package com.gymroutine.app.data.local.dao

import androidx.room.*
import com.gymroutine.app.data.local.entities.WorkoutDayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDayDao {
    @Query("SELECT * FROM workout_days WHERE routineId = :routineId ORDER BY orderIndex ASC")
    fun getDaysForRoutine(routineId: Long): Flow<List<WorkoutDayEntity>>

    @Query("SELECT * FROM workout_days WHERE id = :id")
    suspend fun getDayById(id: Long): WorkoutDayEntity?

    @Query("SELECT * FROM workout_days WHERE dayName LIKE '%' || :query || '%'")
    fun searchDays(query: String): Flow<List<WorkoutDayEntity>>

    @Query("SELECT COUNT(*) FROM workout_days WHERE routineId = :routineId")
    suspend fun getDayCountForRoutine(routineId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: WorkoutDayEntity): Long

    @Update
    suspend fun updateDay(day: WorkoutDayEntity)

    @Delete
    suspend fun deleteDay(day: WorkoutDayEntity)
}
