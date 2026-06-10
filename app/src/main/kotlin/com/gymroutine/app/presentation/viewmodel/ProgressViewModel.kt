package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class ProgressPoint(val label: String, val value: Float)

data class ProgressUiState(
    val totalSessions: Int = 0,
    val totalRoutines: Int = 0,
    val thisWeekSessions: Int = 0,
    val volumeData: List<ProgressPoint> = emptyList(),
    val isLoading: Boolean = true
)

@HiltViewModel
class ProgressViewModel @Inject constructor(
    private val sessionRepository: WorkoutSessionRepository,
    private val routineRepository: RoutineRepository,
    private val exerciseLogRepository: ExerciseLogRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProgressUiState())
    val uiState: StateFlow<ProgressUiState> = _uiState.asStateFlow()
    private val dateFmt = SimpleDateFormat("MM/dd", Locale.getDefault())

    init { loadData() }

    private fun loadData() {
        viewModelScope.launch {
            combine(
                sessionRepository.getAllSessions(),
                routineRepository.getRoutineCount()
            ) { sessions, routineCount ->
                val weekMs = 7L * 24 * 60 * 60 * 1000
                val weekSessions = sessions.count { System.currentTimeMillis() - it.sessionDate < weekMs }

                val recentSessions = sessions.takeLast(8)
                val volumeData = recentSessions.map { session ->
                    val vol = exerciseLogRepository.getTotalVolumeForSession(session.id) ?: 0f
                    ProgressPoint(dateFmt.format(Date(session.sessionDate)), vol)
                }

                ProgressUiState(
                    totalSessions = sessions.size,
                    totalRoutines = routineCount,
                    thisWeekSessions = weekSessions,
                    volumeData = volumeData,
                    isLoading = false
                )
            }.collect { state ->
                _uiState.update { state }
            }
        }
    }
}
