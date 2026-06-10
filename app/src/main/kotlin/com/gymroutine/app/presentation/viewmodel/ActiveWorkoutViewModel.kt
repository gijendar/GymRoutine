package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.*
import com.gymroutine.app.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExerciseWithSets(
    val exercise: Exercise,
    val sets: List<SetEntry> = listOf(SetEntry(setNumber = 1)),
    val isExpanded: Boolean = false,
    val lastSessionWeight: Float? = null,
    val lastSessionReps: Int? = null
)

data class ActiveWorkoutUiState(
    val session: WorkoutSession? = null,
    val dayName: String = "",
    val exercisesWithSets: List<ExerciseWithSets> = emptyList(),
    val isLoading: Boolean = true,
    val elapsedSeconds: Int = 0,
    val showFinishDialog: Boolean = false
)

@HiltViewModel
class ActiveWorkoutViewModel @Inject constructor(
    private val workoutDayRepository: WorkoutDayRepository,
    private val exerciseRepository: ExerciseRepository,
    private val sessionRepository: WorkoutSessionRepository,
    private val exerciseLogRepository: ExerciseLogRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val sessionId: Long = checkNotNull(savedStateHandle["sessionId"])
    private val dayId: Long = checkNotNull(savedStateHandle["dayId"])

    private val _uiState = MutableStateFlow(ActiveWorkoutUiState())
    val uiState: StateFlow<ActiveWorkoutUiState> = _uiState.asStateFlow()

    private var timerJob: kotlinx.coroutines.Job? = null

    init {
        loadData()
        startTimer()
    }

    private fun loadData() {
        viewModelScope.launch {
            val session = sessionRepository.getSessionById(sessionId)
            val day = workoutDayRepository.getDayById(dayId)
            _uiState.update { it.copy(session = session, dayName = day?.name ?: "") }

            exerciseRepository.getExercisesForDay(dayId).collect { exercises ->
                val exercisesWithSets = exercises.map { exercise ->
                    val lastLog = exerciseLogRepository.getLastLogForExercise(exercise.id)
                    ExerciseWithSets(
                        exercise = exercise,
                        sets = listOf(SetEntry(setNumber = 1)),
                        isExpanded = false,
                        lastSessionWeight = lastLog?.weight,
                        lastSessionReps = lastLog?.reps
                    )
                }
                _uiState.update { it.copy(exercisesWithSets = exercisesWithSets, isLoading = false) }
            }
        }
    }

    private fun startTimer() {
        timerJob = viewModelScope.launch {
            while (true) {
                kotlinx.coroutines.delay(1000)
                _uiState.update { it.copy(elapsedSeconds = it.elapsedSeconds + 1) }
            }
        }
    }

    fun toggleExercise(exerciseId: Long) {
        _uiState.update { state ->
            state.copy(
                exercisesWithSets = state.exercisesWithSets.map { item ->
                    if (item.exercise.id == exerciseId) item.copy(isExpanded = !item.isExpanded)
                    else item
                }
            )
        }
    }

    fun updateSet(exerciseId: Long, setIndex: Int, weight: String, reps: String) {
        _uiState.update { state ->
            state.copy(
                exercisesWithSets = state.exercisesWithSets.map { item ->
                    if (item.exercise.id == exerciseId) {
                        val updatedSets = item.sets.toMutableList()
                        if (setIndex < updatedSets.size) {
                            updatedSets[setIndex] = updatedSets[setIndex].copy(weight = weight, reps = reps)
                        }
                        item.copy(sets = updatedSets)
                    } else item
                }
            )
        }
    }

    fun addSet(exerciseId: Long) {
        _uiState.update { state ->
            state.copy(
                exercisesWithSets = state.exercisesWithSets.map { item ->
                    if (item.exercise.id == exerciseId) {
                        val newSetNumber = item.sets.size + 1
                        item.copy(sets = item.sets + SetEntry(setNumber = newSetNumber))
                    } else item
                }
            )
        }
    }

    fun removeSet(exerciseId: Long, setIndex: Int) {
        _uiState.update { state ->
            state.copy(
                exercisesWithSets = state.exercisesWithSets.map { item ->
                    if (item.exercise.id == exerciseId && item.sets.size > 1) {
                        val updated = item.sets.toMutableList()
                            .also { it.removeAt(setIndex) }
                            .mapIndexed { i, s -> s.copy(setNumber = i + 1) }
                        item.copy(sets = updated)
                    } else item
                }
            )
        }
    }

    fun showFinishDialog() = _uiState.update { it.copy(showFinishDialog = true) }
    fun hideFinishDialog() = _uiState.update { it.copy(showFinishDialog = false) }

    fun finishWorkout(onComplete: (Long) -> Unit) {
        viewModelScope.launch {
            timerJob?.cancel()
            val endTime = System.currentTimeMillis()
            val session = _uiState.value.session ?: return@launch
            sessionRepository.updateSession(session.copy(endTime = endTime))

            val logs = _uiState.value.exercisesWithSets.flatMap { item ->
                item.sets.mapNotNull { set ->
                    val w = set.weight.toFloatOrNull() ?: return@mapNotNull null
                    val r = set.reps.toIntOrNull() ?: return@mapNotNull null
                    if (w <= 0f || r <= 0) return@mapNotNull null
                    ExerciseLog(
                        workoutSessionId = sessionId,
                        exerciseId = item.exercise.id,
                        setNumber = set.setNumber,
                        weight = w,
                        reps = r
                    )
                }
            }
            exerciseLogRepository.insertLogs(logs)
            onComplete(sessionId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
