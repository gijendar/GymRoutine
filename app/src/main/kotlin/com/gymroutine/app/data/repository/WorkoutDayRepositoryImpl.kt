package com.gymroutine.app.data.repository

import com.gymroutine.app.data.local.dao.ExerciseDao
import com.gymroutine.app.data.local.dao.WorkoutDayDao
import com.gymroutine.app.data.local.entities.WorkoutDayEntity
import com.gymroutine.app.domain.model.WorkoutDay
import com.gymroutine.app.domain.repository.WorkoutDayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WorkoutDayRepositoryImpl @Inject constructor(
    private val workoutDayDao: WorkoutDayDao,
    private val exerciseDao: ExerciseDao
) : WorkoutDayRepository {

    override fun getDaysForRoutine(routineId: Long): Flow<List<WorkoutDay>> =
        workoutDayDao.getDaysForRoutine(routineId).map { list ->
            list.map { entity ->
                val exerciseCount = exerciseDao.getExerciseCountForDay(entity.id)
                entity.toDomain(exerciseCount = exerciseCount)
            }
        }

    override suspend fun getDayById(id: Long): WorkoutDay? =
        workoutDayDao.getDayById(id)?.toDomain()

    override fun searchDays(query: String): Flow<List<WorkoutDay>> =
        workoutDayDao.searchDays(query).map { list -> list.map { it.toDomain() } }

    override suspend fun insertDay(day: WorkoutDay): Long =
        workoutDayDao.insertDay(day.toEntity())

    override suspend fun updateDay(day: WorkoutDay) =
        workoutDayDao.updateDay(day.toEntity())

    override suspend fun deleteDay(day: WorkoutDay) =
        workoutDayDao.deleteDay(day.toEntity())

    private fun WorkoutDayEntity.toDomain(exerciseCount: Int = 0) = WorkoutDay(
        id = id, routineId = routineId, name = dayName, orderIndex = orderIndex, exerciseCount = exerciseCount
    )
    private fun WorkoutDay.toEntity() = WorkoutDayEntity(id = id, routineId = routineId, dayName = name, orderIndex = orderIndex)
}
