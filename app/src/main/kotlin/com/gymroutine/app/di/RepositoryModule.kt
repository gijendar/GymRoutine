package com.gymroutine.app.di

import com.gymroutine.app.data.repository.*
import com.gymroutine.app.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun bindRoutineRepository(impl: RoutineRepositoryImpl): RoutineRepository

    @Binds @Singleton
    abstract fun bindWorkoutDayRepository(impl: WorkoutDayRepositoryImpl): WorkoutDayRepository

    @Binds @Singleton
    abstract fun bindExerciseRepository(impl: ExerciseRepositoryImpl): ExerciseRepository

    @Binds @Singleton
    abstract fun bindWorkoutSessionRepository(impl: WorkoutSessionRepositoryImpl): WorkoutSessionRepository

    @Binds @Singleton
    abstract fun bindExerciseLogRepository(impl: ExerciseLogRepositoryImpl): ExerciseLogRepository
}
