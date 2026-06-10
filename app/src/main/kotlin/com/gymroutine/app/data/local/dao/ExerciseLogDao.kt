package com.gymroutine.app.data.local.dao

import androidx.room.*
import com.gymroutine.app.data.local.entities.ExerciseLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseLogDao {
    @Query("SELECT * FROM exercise_logs WHERE workoutSessionId = :sessionId ORDER BY setNumber ASC")
    fun getLogsForSession(sessionId: Long): Flow<List<ExerciseLogEntity>>

    @Query("SELECT * FROM exercise_logs WHERE exerciseId = :exerciseId ORDER BY workoutSessionId DESC")
    fun getLogsForExercise(exerciseId: Long): Flow<List<ExerciseLogEntity>>

    @Query("SELECT * FROM exercise_logs WHERE exerciseId = :exerciseId AND workoutSessionId = :sessionId ORDER BY setNumber ASC")
    fun getLogsForExerciseInSession(exerciseId: Long, sessionId: Long): Flow<List<ExerciseLogEntity>>

    @Query("""
        SELECT el.* FROM exercise_logs el
        INNER JOIN workout_sessions ws ON el.workoutSessionId = ws.id
        WHERE el.exerciseId = :exerciseId
        ORDER BY ws.sessionDate DESC
        LIMIT 1
    """)
    suspend fun getLastLogForExercise(exerciseId: Long): ExerciseLogEntity?

    @Query("""
        SELECT MAX(weight) FROM exercise_logs WHERE exerciseId = :exerciseId
    """)
    suspend fun getBestWeightForExercise(exerciseId: Long): Float?

    @Query("""
        SELECT MAX(reps) FROM exercise_logs WHERE exerciseId = :exerciseId
    """)
    suspend fun getBestRepsForExercise(exerciseId: Long): Int?

    @Query("""
        SELECT SUM(weight * reps) FROM exercise_logs
        WHERE workoutSessionId = :sessionId
    """)
    suspend fun getTotalVolumeForSession(sessionId: Long): Float?

    @Query("SELECT COUNT(DISTINCT workoutSessionId) FROM exercise_logs WHERE exerciseId = :exerciseId")
    suspend fun getSessionCountForExercise(exerciseId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: ExerciseLogEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLogs(logs: List<ExerciseLogEntity>)

    @Update
    suspend fun updateLog(log: ExerciseLogEntity)

    @Delete
    suspend fun deleteLog(log: ExerciseLogEntity)

    @Query("DELETE FROM exercise_logs WHERE workoutSessionId = :sessionId AND exerciseId = :exerciseId")
    suspend fun deleteLogsForExerciseInSession(sessionId: Long, exerciseId: Long)
}
