package com.gymroutine.app.data.repository

import com.gymroutine.app.data.local.dao.ExerciseLogDao
import com.gymroutine.app.data.local.entities.ExerciseLogEntity
import com.gymroutine.app.domain.model.ExerciseLog
import com.gymroutine.app.domain.repository.ExerciseLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseLogRepositoryImpl @Inject constructor(
    private val exerciseLogDao: ExerciseLogDao
) : ExerciseLogRepository {

    override fun getLogsForSession(sessionId: Long): Flow<List<ExerciseLog>> =
        exerciseLogDao.getLogsForSession(sessionId).map { list -> list.map { it.toDomain() } }

    override fun getLogsForExercise(exerciseId: Long): Flow<List<ExerciseLog>> =
        exerciseLogDao.getLogsForExercise(exerciseId).map { list -> list.map { it.toDomain() } }

    override fun getLogsForExerciseInSession(exerciseId: Long, sessionId: Long): Flow<List<ExerciseLog>> =
        exerciseLogDao.getLogsForExerciseInSession(exerciseId, sessionId).map { list -> list.map { it.toDomain() } }

    override suspend fun getLastLogForExercise(exerciseId: Long): ExerciseLog? =
        exerciseLogDao.getLastLogForExercise(exerciseId)?.toDomain()

    override suspend fun getBestWeightForExercise(exerciseId: Long): Float? =
        exerciseLogDao.getBestWeightForExercise(exerciseId)

    override suspend fun getBestRepsForExercise(exerciseId: Long): Int? =
        exerciseLogDao.getBestRepsForExercise(exerciseId)

    override suspend fun getTotalVolumeForSession(sessionId: Long): Float? =
        exerciseLogDao.getTotalVolumeForSession(sessionId)

    override suspend fun getSessionCountForExercise(exerciseId: Long): Int =
        exerciseLogDao.getSessionCountForExercise(exerciseId)

    override suspend fun insertLog(log: ExerciseLog): Long =
        exerciseLogDao.insertLog(log.toEntity())

    override suspend fun insertLogs(logs: List<ExerciseLog>) =
        exerciseLogDao.insertLogs(logs.map { it.toEntity() })

    override suspend fun deleteLogsForExerciseInSession(sessionId: Long, exerciseId: Long) =
        exerciseLogDao.deleteLogsForExerciseInSession(sessionId, exerciseId)

    private fun ExerciseLogEntity.toDomain() = ExerciseLog(
        id = id, workoutSessionId = workoutSessionId, exerciseId = exerciseId,
        setNumber = setNumber, weight = weight, reps = reps
    )
    private fun ExerciseLog.toEntity() = ExerciseLogEntity(
        id = id, workoutSessionId = workoutSessionId, exerciseId = exerciseId,
        setNumber = setNumber, weight = weight, reps = reps
    )
}
