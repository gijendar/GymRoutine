package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.Routine
import com.gymroutine.app.domain.model.WorkoutDay
import com.gymroutine.app.domain.model.WorkoutSession
import com.gymroutine.app.domain.repository.RoutineRepository
import com.gymroutine.app.domain.repository.WorkoutDayRepository
import com.gymroutine.app.domain.repository.WorkoutSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StartWorkoutUiState(
    val routines: List<Routine> = emptyList(),
    val selectedRoutineId: Long? = null,
    val days: List<WorkoutDay> = emptyList(),
    val isLoading: Boolean = true,
    val isStarting: Boolean = false
)

@HiltViewModel
class StartWorkoutViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    private val workoutDayRepository: WorkoutDayRepository,
    private val sessionRepository: WorkoutSessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StartWorkoutUiState())
    val uiState: StateFlow<StartWorkoutUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            routineRepository.getAllRoutines().collect { routines ->
                _uiState.update { it.copy(routines = routines, isLoading = false) }
            }
        }
    }

    fun selectRoutine(routineId: Long) {
        _uiState.update { it.copy(selectedRoutineId = routineId, days = emptyList()) }
        viewModelScope.launch {
            workoutDayRepository.getDaysForRoutine(routineId).collect { days ->
                _uiState.update { it.copy(days = days) }
            }
        }
    }

    fun startWorkout(dayId: Long, onStarted: (Long, Long) -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isStarting = true) }
            val sessionId = sessionRepository.insertSession(WorkoutSession(workoutDayId = dayId))
            _uiState.update { it.copy(isStarting = false) }
            onStarted(sessionId, dayId)
        }
    }
}
