package com.gymroutine.app.data.repository

import com.gymroutine.app.data.local.dao.WorkoutSessionDao
import com.gymroutine.app.data.local.entities.WorkoutSessionEntity
import com.gymroutine.app.domain.model.WorkoutSession
import com.gymroutine.app.domain.repository.WorkoutSessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WorkoutSessionRepositoryImpl @Inject constructor(
    private val workoutSessionDao: WorkoutSessionDao
) : WorkoutSessionRepository {

    override fun getAllSessions(): Flow<List<WorkoutSession>> =
        workoutSessionDao.getAllSessions().map { list -> list.map { it.toDomain() } }

    override fun getSessionsForDay(dayId: Long): Flow<List<WorkoutSession>> =
        workoutSessionDao.getSessionsForDay(dayId).map { list -> list.map { it.toDomain() } }

    override suspend fun getSessionById(id: Long): WorkoutSession? =
        workoutSessionDao.getSessionById(id)?.toDomain()

    override suspend fun getLastSession(): WorkoutSession? =
        workoutSessionDao.getLastSession()?.toDomain()

    override fun getTotalSessionCount(): Flow<Int> = workoutSessionDao.getTotalSessionCount()

    override suspend fun insertSession(session: WorkoutSession): Long =
        workoutSessionDao.insertSession(session.toEntity())

    override suspend fun updateSession(session: WorkoutSession) =
        workoutSessionDao.updateSession(session.toEntity())

    override suspend fun deleteSession(session: WorkoutSession) =
        workoutSessionDao.deleteSession(session.toEntity())

    private fun WorkoutSessionEntity.toDomain() = WorkoutSession(
        id = id, workoutDayId = workoutDayId, sessionDate = sessionDate, startTime = startTime, endTime = endTime
    )
    private fun WorkoutSession.toEntity() = WorkoutSessionEntity(
        id = id, workoutDayId = workoutDayId, sessionDate = sessionDate, startTime = startTime, endTime = endTime
    )
}
