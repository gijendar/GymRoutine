package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.Exercise
import com.gymroutine.app.domain.model.WorkoutDay
import com.gymroutine.app.domain.repository.ExerciseRepository
import com.gymroutine.app.domain.repository.WorkoutDayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WorkoutDayUiState(
    val category: WorkoutDay? = null,
    val exercises: List<Exercise> = emptyList(),
    val isLoading: Boolean = true,
    val showAddExerciseDialog: Boolean = false,
    val showEditExerciseDialog: Boolean = false,
    val editingExercise: Exercise? = null,
    val newExerciseName: String = ""
)

@HiltViewModel
class WorkoutDayViewModel @Inject constructor(
    private val workoutDayRepository: WorkoutDayRepository,
    private val exerciseRepository: ExerciseRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val dayId: Long = checkNotNull(savedStateHandle["dayId"])
    private val _uiState = MutableStateFlow(WorkoutDayUiState())
    val uiState: StateFlow<WorkoutDayUiState> = _uiState.asStateFlow()

    init { loadData() }

    private fun loadData() {
        viewModelScope.launch {
            val category = workoutDayRepository.getDayById(dayId)
            _uiState.update { it.copy(category = category) }
        }
        viewModelScope.launch {
            exerciseRepository.getExercisesForDay(dayId).collect { exercises ->
                _uiState.update { it.copy(exercises = exercises, isLoading = false) }
            }
        }
    }

    fun showAddDialog() = _uiState.update { it.copy(showAddExerciseDialog = true, newExerciseName = "") }
    fun hideAddDialog() = _uiState.update { it.copy(showAddExerciseDialog = false, newExerciseName = "") }

    fun showEditDialog(exercise: Exercise) =
        _uiState.update { it.copy(showEditExerciseDialog = true, editingExercise = exercise, newExerciseName = exercise.name) }

    fun hideEditDialog() =
        _uiState.update { it.copy(showEditExerciseDialog = false, editingExercise = null, newExerciseName = "") }

    fun updateExerciseName(name: String) = _uiState.update { it.copy(newExerciseName = name) }

    fun addExercise() {
        val name = _uiState.value.newExerciseName.trim()
        if (name.isBlank()) return
        viewModelScope.launch {
            val orderIndex = _uiState.value.exercises.size
            exerciseRepository.insertExercise(Exercise(workoutDayId = dayId, name = name, orderIndex = orderIndex))
            hideAddDialog()
        }
    }

    fun updateExercise() {
        val state = _uiState.value
        val exercise = state.editingExercise ?: return
        val name = state.newExerciseName.trim()
        if (name.isBlank()) return
        viewModelScope.launch {
            exerciseRepository.updateExercise(exercise.copy(name = name))
            hideEditDialog()
        }
    }

    fun deleteExercise(exercise: Exercise) {
        viewModelScope.launch { exerciseRepository.deleteExercise(exercise) }
    }
}
