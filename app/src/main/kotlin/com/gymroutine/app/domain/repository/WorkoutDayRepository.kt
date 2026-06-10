package com.gymroutine.app.domain.repository

import com.gymroutine.app.domain.model.WorkoutDay
import kotlinx.coroutines.flow.Flow

interface WorkoutDayRepository {
    fun getDaysForRoutine(routineId: Long): Flow<List<WorkoutDay>>
    suspend fun getDayById(id: Long): WorkoutDay?
    fun searchDays(query: String): Flow<List<WorkoutDay>>
    suspend fun insertDay(day: WorkoutDay): Long
    suspend fun updateDay(day: WorkoutDay)
    suspend fun deleteDay(day: WorkoutDay)
}
