package com.gymroutine.app.domain.repository

import com.gymroutine.app.domain.model.WorkoutSession
import kotlinx.coroutines.flow.Flow

interface WorkoutSessionRepository {
    fun getAllSessions(): Flow<List<WorkoutSession>>
    fun getSessionsForDay(dayId: Long): Flow<List<WorkoutSession>>
    suspend fun getSessionById(id: Long): WorkoutSession?
    suspend fun getLastSession(): WorkoutSession?
    fun getTotalSessionCount(): Flow<Int>
    suspend fun insertSession(session: WorkoutSession): Long
    suspend fun updateSession(session: WorkoutSession)
    suspend fun deleteSession(session: WorkoutSession)
}
