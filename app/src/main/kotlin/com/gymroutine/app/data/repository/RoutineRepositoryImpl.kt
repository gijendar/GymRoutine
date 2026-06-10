package com.gymroutine.app.data.repository

import com.gymroutine.app.data.local.dao.RoutineDao
import com.gymroutine.app.data.local.dao.WorkoutDayDao
import com.gymroutine.app.data.local.dao.ExerciseDao
import com.gymroutine.app.data.local.entities.RoutineEntity
import com.gymroutine.app.domain.model.Routine
import com.gymroutine.app.domain.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoutineRepositoryImpl @Inject constructor(
    private val routineDao: RoutineDao,
    private val workoutDayDao: WorkoutDayDao,
    private val exerciseDao: ExerciseDao
) : RoutineRepository {

    override fun getAllRoutines(): Flow<List<Routine>> =
        routineDao.getAllRoutines().map { list ->
            list.map { entity ->
                val dayCount = workoutDayDao.getDayCountForRoutine(entity.id)
                entity.toDomain(dayCount = dayCount)
            }
        }

    override suspend fun getRoutineById(id: Long): Routine? =
        routineDao.getRoutineById(id)?.toDomain()

    override fun searchRoutines(query: String): Flow<List<Routine>> =
        routineDao.searchRoutines(query).map { list -> list.map { it.toDomain() } }

    override suspend fun insertRoutine(routine: Routine): Long =
        routineDao.insertRoutine(routine.toEntity())

    override suspend fun updateRoutine(routine: Routine) =
        routineDao.updateRoutine(routine.toEntity())

    override suspend fun deleteRoutine(routine: Routine) =
        routineDao.deleteRoutine(routine.toEntity())

    override fun getRoutineCount(): Flow<Int> = routineDao.getRoutineCount()

    private fun RoutineEntity.toDomain(dayCount: Int = 0) = Routine(
        id = id, name = routineName, createdAt = createdAt, dayCount = dayCount
    )
    private fun Routine.toEntity() = RoutineEntity(id = id, routineName = name, createdAt = createdAt)
}
