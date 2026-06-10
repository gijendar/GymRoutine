package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.Routine
import com.gymroutine.app.domain.model.WorkoutDay
import com.gymroutine.app.domain.repository.RoutineRepository
import com.gymroutine.app.domain.repository.WorkoutDayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RoutineUiState(
    val routine: Routine? = null,
    val categories: List<WorkoutDay> = emptyList(),
    val isLoading: Boolean = true,
    val showAddCategoryDialog: Boolean = false,
    val showEditCategoryDialog: Boolean = false,
    val editingCategory: WorkoutDay? = null,
    val newCategoryName: String = "",
    val error: String? = null
)

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    private val workoutDayRepository: WorkoutDayRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val routineId: Long = checkNotNull(savedStateHandle["routineId"])
    private val _uiState = MutableStateFlow(RoutineUiState())
    val uiState: StateFlow<RoutineUiState> = _uiState.asStateFlow()

    init { loadData() }

    private fun loadData() {
        viewModelScope.launch {
            val routine = routineRepository.getRoutineById(routineId)
            _uiState.update { it.copy(routine = routine) }
        }
        viewModelScope.launch {
            workoutDayRepository.getDaysForRoutine(routineId).collect { days ->
                _uiState.update { it.copy(categories = days, isLoading = false) }
            }
        }
    }

    fun showAddCategoryDialog() = _uiState.update { it.copy(showAddCategoryDialog = true, newCategoryName = "") }
    fun hideAddCategoryDialog() = _uiState.update { it.copy(showAddCategoryDialog = false, newCategoryName = "") }

    fun showEditCategoryDialog(category: WorkoutDay) =
        _uiState.update { it.copy(showEditCategoryDialog = true, editingCategory = category, newCategoryName = category.name) }

    fun hideEditCategoryDialog() =
        _uiState.update { it.copy(showEditCategoryDialog = false, editingCategory = null, newCategoryName = "") }

    fun updateNewCategoryName(name: String) = _uiState.update { it.copy(newCategoryName = name) }

    fun addCategory() {
        val name = _uiState.value.newCategoryName.trim()
        if (name.isBlank()) return
        viewModelScope.launch {
            val orderIndex = _uiState.value.categories.size
            workoutDayRepository.insertDay(WorkoutDay(routineId = routineId, name = name, orderIndex = orderIndex))
            hideAddCategoryDialog()
        }
    }

    fun updateCategory() {
        val state = _uiState.value
        val category = state.editingCategory ?: return
        val name = state.newCategoryName.trim()
        if (name.isBlank()) return
        viewModelScope.launch {
            workoutDayRepository.updateDay(category.copy(name = name))
            hideEditCategoryDialog()
        }
    }

    fun deleteCategory(category: WorkoutDay) {
        viewModelScope.launch { workoutDayRepository.deleteDay(category) }
    }

    fun updateRoutineName(name: String) {
        val routine = _uiState.value.routine ?: return
        viewModelScope.launch { routineRepository.updateRoutine(routine.copy(name = name)) }
    }
}
