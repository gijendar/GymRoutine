package com.gymroutine.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gymroutine.app.data.local.dao.*
import com.gymroutine.app.data.local.entities.*

@Database(
    entities = [
        RoutineEntity::class,
        WorkoutDayEntity::class,
        ExerciseEntity::class,
        WorkoutSessionEntity::class,
        ExerciseLogEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class GymRoutineDatabase : RoomDatabase() {
    abstract fun routineDao(): RoutineDao
    abstract fun workoutDayDao(): WorkoutDayDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
    abstract fun exerciseLogDao(): ExerciseLogDao

    companion object {
        const val DATABASE_NAME = "gymroutine.db"

        /**
         * Migration 1 → 2: No structural change needed — workout_days table
         * is reused as exercise_categories. Schema is identical.
         * This migration is a no-op that simply increments the version so
         * existing data is preserved.
         */
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // No schema changes — table reuse strategy preserves data
            }
        }
    }
}
