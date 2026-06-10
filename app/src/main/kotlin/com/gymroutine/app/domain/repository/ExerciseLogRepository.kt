package com.gymroutine.app.domain.repository

import com.gymroutine.app.domain.model.ExerciseLog
import kotlinx.coroutines.flow.Flow

interface ExerciseLogRepository {
    fun getLogsForSession(sessionId: Long): Flow<List<ExerciseLog>>
    fun getLogsForExercise(exerciseId: Long): Flow<List<ExerciseLog>>
    fun getLogsForExerciseInSession(exerciseId: Long, sessionId: Long): Flow<List<ExerciseLog>>
    suspend fun getLastLogForExercise(exerciseId: Long): ExerciseLog?
    suspend fun getBestWeightForExercise(exerciseId: Long): Float?
    suspend fun getBestRepsForExercise(exerciseId: Long): Int?
    suspend fun getTotalVolumeForSession(sessionId: Long): Float?
    suspend fun getSessionCountForExercise(exerciseId: Long): Int
    suspend fun insertLog(log: ExerciseLog): Long
    suspend fun insertLogs(logs: List<ExerciseLog>)
    suspend fun deleteLogsForExerciseInSession(sessionId: Long, exerciseId: Long)
}
