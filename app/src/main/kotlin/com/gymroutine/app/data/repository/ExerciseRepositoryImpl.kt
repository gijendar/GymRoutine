package com.gymroutine.app.data.repository

import com.gymroutine.app.data.local.dao.ExerciseDao
import com.gymroutine.app.data.local.entities.ExerciseEntity
import com.gymroutine.app.domain.model.Exercise
import com.gymroutine.app.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val exerciseDao: ExerciseDao
) : ExerciseRepository {

    override fun getExercisesForDay(dayId: Long): Flow<List<Exercise>> =
        exerciseDao.getExercisesForDay(dayId).map { list -> list.map { it.toDomain() } }

    override suspend fun getExerciseById(id: Long): Exercise? =
        exerciseDao.getExerciseById(id)?.toDomain()

    override fun searchExercises(query: String): Flow<List<Exercise>> =
        exerciseDao.searchExercises(query).map { list -> list.map { it.toDomain() } }

    override suspend fun insertExercise(exercise: Exercise): Long =
        exerciseDao.insertExercise(exercise.toEntity())

    override suspend fun updateExercise(exercise: Exercise) =
        exerciseDao.updateExercise(exercise.toEntity())

    override suspend fun deleteExercise(exercise: Exercise) =
        exerciseDao.deleteExercise(exercise.toEntity())

    private fun ExerciseEntity.toDomain() = Exercise(id = id, workoutDayId = workoutDayId, name = exerciseName, orderIndex = orderIndex)
    private fun Exercise.toEntity() = ExerciseEntity(id = id, workoutDayId = workoutDayId, exerciseName = name, orderIndex = orderIndex)
}
