package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.WorkoutSession
import com.gymroutine.app.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WorkoutCompleteUiState(
    val session: WorkoutSession? = null,
    val dayName: String = "",
    val totalExercises: Int = 0,
    val totalSets: Int = 0,
    val totalVolume: Float = 0f,
    val durationMinutes: Long = 0L,
    val isLoading: Boolean = true
)

@HiltViewModel
class WorkoutCompleteViewModel @Inject constructor(
    private val sessionRepository: WorkoutSessionRepository,
    private val workoutDayRepository: WorkoutDayRepository,
    private val exerciseLogRepository: ExerciseLogRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val sessionId: Long = checkNotNull(savedStateHandle["sessionId"])
    private val _uiState = MutableStateFlow(WorkoutCompleteUiState())
    val uiState: StateFlow<WorkoutCompleteUiState> = _uiState.asStateFlow()

    init { loadData() }

    private fun loadData() {
        viewModelScope.launch {
            val session = sessionRepository.getSessionById(sessionId) ?: return@launch
            val day = workoutDayRepository.getDayById(session.workoutDayId)
            val totalVolume = exerciseLogRepository.getTotalVolumeForSession(sessionId) ?: 0f
            val duration = if (session.endTime != null) (session.endTime - session.startTime) / 60000L else 0L

            exerciseLogRepository.getLogsForSession(sessionId).collect { logs ->
                val exerciseIds = logs.map { it.exerciseId }.distinct()
                _uiState.update {
                    it.copy(
                        session = session,
                        dayName = day?.name ?: "",
                        totalExercises = exerciseIds.size,
                        totalSets = logs.size,
                        totalVolume = totalVolume,
                        durationMinutes = duration,
                        isLoading = false
                    )
                }
            }
        }
    }
}
