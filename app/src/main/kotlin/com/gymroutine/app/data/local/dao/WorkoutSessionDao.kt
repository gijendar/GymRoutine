package com.gymroutine.app.data.local.dao

import androidx.room.*
import com.gymroutine.app.data.local.entities.WorkoutSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutSessionDao {
    @Query("SELECT * FROM workout_sessions ORDER BY sessionDate DESC")
    fun getAllSessions(): Flow<List<WorkoutSessionEntity>>

    @Query("SELECT * FROM workout_sessions WHERE workoutDayId = :dayId ORDER BY sessionDate DESC")
    fun getSessionsForDay(dayId: Long): Flow<List<WorkoutSessionEntity>>

    @Query("SELECT * FROM workout_sessions WHERE id = :id")
    suspend fun getSessionById(id: Long): WorkoutSessionEntity?

    @Query("SELECT * FROM workout_sessions ORDER BY sessionDate DESC LIMIT 1")
    suspend fun getLastSession(): WorkoutSessionEntity?

    @Query("SELECT COUNT(*) FROM workout_sessions")
    fun getTotalSessionCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: WorkoutSessionEntity): Long

    @Update
    suspend fun updateSession(session: WorkoutSessionEntity)

    @Delete
    suspend fun deleteSession(session: WorkoutSessionEntity)
}
