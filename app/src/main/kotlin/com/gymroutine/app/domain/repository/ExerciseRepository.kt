package com.gymroutine.app.domain.repository

import com.gymroutine.app.domain.model.Exercise
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    fun getExercisesForDay(dayId: Long): Flow<List<Exercise>>
    suspend fun getExerciseById(id: Long): Exercise?
    fun searchExercises(query: String): Flow<List<Exercise>>
    suspend fun insertExercise(exercise: Exercise): Long
    suspend fun updateExercise(exercise: Exercise)
    suspend fun deleteExercise(exercise: Exercise)
}
