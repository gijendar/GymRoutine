package com.gymroutine.app.di

import android.content.Context
import androidx.room.Room
import com.gymroutine.app.data.local.dao.*
import com.gymroutine.app.data.local.database.GymRoutineDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GymRoutineDatabase =
        Room.databaseBuilder(context, GymRoutineDatabase::class.java, GymRoutineDatabase.DATABASE_NAME)
            .addMigrations(GymRoutineDatabase.MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideRoutineDao(db: GymRoutineDatabase): RoutineDao = db.routineDao()
    @Provides fun provideWorkoutDayDao(db: GymRoutineDatabase): WorkoutDayDao = db.workoutDayDao()
    @Provides fun provideExerciseDao(db: GymRoutineDatabase): ExerciseDao = db.exerciseDao()
    @Provides fun provideWorkoutSessionDao(db: GymRoutineDatabase): WorkoutSessionDao = db.workoutSessionDao()
    @Provides fun provideExerciseLogDao(db: GymRoutineDatabase): ExerciseLogDao = db.exerciseLogDao()
}
