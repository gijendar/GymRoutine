package com.gymroutine.app.data.repository;

import com.gymroutine.app.data.local.dao.ExerciseDao;
import com.gymroutine.app.data.local.dao.WorkoutDayDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class WorkoutDayRepositoryImpl_Factory implements Factory<WorkoutDayRepositoryImpl> {
  private final Provider<WorkoutDayDao> workoutDayDaoProvider;

  private final Provider<ExerciseDao> exerciseDaoProvider;

  private WorkoutDayRepositoryImpl_Factory(Provider<WorkoutDayDao> workoutDayDaoProvider,
      Provider<ExerciseDao> exerciseDaoProvider) {
    this.workoutDayDaoProvider = workoutDayDaoProvider;
    this.exerciseDaoProvider = exerciseDaoProvider;
  }

  @Override
  public WorkoutDayRepositoryImpl get() {
    return newInstance(workoutDayDaoProvider.get(), exerciseDaoProvider.get());
  }

  public static WorkoutDayRepositoryImpl_Factory create(
      Provider<WorkoutDayDao> workoutDayDaoProvider, Provider<ExerciseDao> exerciseDaoProvider) {
    return new WorkoutDayRepositoryImpl_Factory(workoutDayDaoProvider, exerciseDaoProvider);
  }

  public static WorkoutDayRepositoryImpl newInstance(WorkoutDayDao workoutDayDao,
      ExerciseDao exerciseDao) {
    return new WorkoutDayRepositoryImpl(workoutDayDao, exerciseDao);
  }
}
