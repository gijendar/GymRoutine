package com.gymroutine.app.domain.repository

import com.gymroutine.app.domain.model.Routine
import kotlinx.coroutines.flow.Flow

interface RoutineRepository {
    fun getAllRoutines(): Flow<List<Routine>>
    suspend fun getRoutineById(id: Long): Routine?
    fun searchRoutines(query: String): Flow<List<Routine>>
    suspend fun insertRoutine(routine: Routine): Long
    suspend fun updateRoutine(routine: Routine)
    suspend fun deleteRoutine(routine: Routine)
    fun getRoutineCount(): Flow<Int>
}
