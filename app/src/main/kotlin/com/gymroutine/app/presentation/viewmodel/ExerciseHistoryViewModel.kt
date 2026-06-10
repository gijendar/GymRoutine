package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.Exercise
import com.gymroutine.app.domain.model.ExerciseLog
import com.gymroutine.app.domain.model.WorkoutSession
import com.gymroutine.app.domain.repository.ExerciseLogRepository
import com.gymroutine.app.domain.repository.ExerciseRepository
import com.gymroutine.app.domain.repository.WorkoutSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class HistoryEntry(
    val date: String,
    val sessionId: Long,
    val sets: List<ExerciseLog>
)

data class ExerciseHistoryUiState(
    val exercise: Exercise? = null,
    val bestWeight: Float? = null,
    val bestReps: Int? = null,
    val totalSessions: Int = 0,
    val historyEntries: List<HistoryEntry> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class ExerciseHistoryViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val exerciseLogRepository: ExerciseLogRepository,
    private val sessionRepository: WorkoutSessionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val exerciseId: Long = checkNotNull(savedStateHandle["exerciseId"])
    private val _uiState = MutableStateFlow(ExerciseHistoryUiState())
    val uiState: StateFlow<ExerciseHistoryUiState> = _uiState.asStateFlow()
    private val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    init { loadData() }

    private fun loadData() {
        viewModelScope.launch {
            val exercise = exerciseRepository.getExerciseById(exerciseId)
            val bestWeight = exerciseLogRepository.getBestWeightForExercise(exerciseId)
            val bestReps = exerciseLogRepository.getBestRepsForExercise(exerciseId)
            val sessionCount = exerciseLogRepository.getSessionCountForExercise(exerciseId)

            _uiState.update {
                it.copy(
                    exercise = exercise,
                    bestWeight = bestWeight,
                    bestReps = bestReps,
                    totalSessions = sessionCount
                )
            }
        }

        viewModelScope.launch {
            combine(
                exerciseLogRepository.getLogsForExercise(exerciseId),
                sessionRepository.getAllSessions()
            ) { allLogs, sessions ->
                val grouped = allLogs.groupBy { it.workoutSessionId }
                grouped.entries.mapNotNull { (sessionId, logs) ->
                    val session = sessions.find { it.id == sessionId } ?: return@mapNotNull null
                    HistoryEntry(
                        date = dateFormat.format(Date(session.sessionDate)),
                        sessionId = sessionId,
                        sets = logs.sortedBy { it.setNumber }
                    )
                }.sortedByDescending { it.sessionId }
            }.collect { entries ->
                _uiState.update { it.copy(historyEntries = entries, isLoading = false) }
            }
        }
    }
}
