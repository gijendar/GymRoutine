package com.gymroutine.app.data.local.dao

import androidx.room.*
import com.gymroutine.app.data.local.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercises WHERE workoutDayId = :dayId ORDER BY orderIndex ASC")
    fun getExercisesForDay(dayId: Long): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE id = :id")
    suspend fun getExerciseById(id: Long): ExerciseEntity?

    @Query("SELECT * FROM exercises WHERE exerciseName LIKE '%' || :query || '%'")
    fun searchExercises(query: String): Flow<List<ExerciseEntity>>

    @Query("SELECT COUNT(*) FROM exercises WHERE workoutDayId = :dayId")
    suspend fun getExerciseCountForDay(dayId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity): Long

    @Update
    suspend fun updateExercise(exercise: ExerciseEntity)

    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)
}
