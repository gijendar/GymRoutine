package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.Routine
import com.gymroutine.app.domain.repository.RoutineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateRoutineUiState(
    val routineName: String = "",
    val isCreating: Boolean = false,
    val createdRoutineId: Long? = null,
    val error: String? = null
)

@HiltViewModel
class CreateRoutineViewModel @Inject constructor(
    private val routineRepository: RoutineRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateRoutineUiState())
    val uiState: StateFlow<CreateRoutineUiState> = _uiState.asStateFlow()

    fun updateRoutineName(name: String) = _uiState.update { it.copy(routineName = name) }

    fun createRoutine() {
        val name = _uiState.value.routineName.trim()
        if (name.isBlank()) {
            _uiState.update { it.copy(error = "Please enter a routine name") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isCreating = true, error = null) }
            val id = routineRepository.insertRoutine(Routine(name = name))
            _uiState.update { it.copy(isCreating = false, createdRoutineId = id) }
        }
    }
}
