package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.Routine
import com.gymroutine.app.domain.model.WorkoutSession
import com.gymroutine.app.domain.repository.RoutineRepository
import com.gymroutine.app.domain.repository.WorkoutSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val routines: List<Routine> = emptyList(),
    val routineCount: Int = 0,
    val lastSession: WorkoutSession? = null,
    val isLoading: Boolean = true
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    private val sessionRepository: WorkoutSessionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        observeData()
        loadLastSession()
    }

    private fun observeData() {
        viewModelScope.launch {
            combine(
                routineRepository.getAllRoutines(),
                routineRepository.getRoutineCount()
            ) { routines, count ->
                Pair(routines, count)
            }.collect { (routines, count) ->
                _uiState.update { it.copy(routines = routines, routineCount = count, isLoading = false) }
            }
        }
    }

    private fun loadLastSession() {
        viewModelScope.launch {
            val last = sessionRepository.getLastSession()
            _uiState.update { it.copy(lastSession = last) }
        }
    }

    fun deleteRoutine(routine: Routine) {
        viewModelScope.launch { routineRepository.deleteRoutine(routine) }
    }
}
