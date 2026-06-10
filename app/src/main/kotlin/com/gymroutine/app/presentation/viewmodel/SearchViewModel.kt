package com.gymroutine.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gymroutine.app.domain.model.*
import com.gymroutine.app.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val routines: List<Routine> = emptyList(),
    val categories: List<WorkoutDay> = emptyList(),
    val exercises: List<Exercise> = emptyList(),
    val isSearching: Boolean = false
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    private val workoutDayRepository: WorkoutDayRepository,
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun updateQuery(query: String) {
        _uiState.update { it.copy(query = query, isSearching = query.isNotBlank()) }
        if (query.isBlank()) {
            _uiState.update { it.copy(routines = emptyList(), categories = emptyList(), exercises = emptyList()) }
            return
        }
        viewModelScope.launch {
            combine(
                routineRepository.searchRoutines(query),
                workoutDayRepository.searchDays(query),
                exerciseRepository.searchExercises(query)
            ) { r, d, e -> Triple(r, d, e) }.collect { (r, d, e) ->
                _uiState.update { it.copy(routines = r, categories = d, exercises = e, isSearching = false) }
            }
        }
    }
}
