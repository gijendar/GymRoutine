package com.gymroutine.app.data.repository;

import com.gymroutine.app.data.local.dao.ExerciseDao;
import com.gymroutine.app.data.local.dao.RoutineDao;
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
public final class RoutineRepositoryImpl_Factory implements Factory<RoutineRepositoryImpl> {
  private final Provider<RoutineDao> routineDaoProvider;

  private final Provider<WorkoutDayDao> workoutDayDaoProvider;

  private final Provider<ExerciseDao> exerciseDaoProvider;

  private RoutineRepositoryImpl_Factory(Provider<RoutineDao> routineDaoProvider,
      Provider<WorkoutDayDao> workoutDayDaoProvider, Provider<ExerciseDao> exerciseDaoProvider) {
    this.routineDaoProvider = routineDaoProvider;
    this.workoutDayDaoProvider = workoutDayDaoProvider;
    this.exerciseDaoProvider = exerciseDaoProvider;
  }

  @Override
  public RoutineRepositoryImpl get() {
    return newInstance(routineDaoProvider.get(), workoutDayDaoProvider.get(), exerciseDaoProvider.get());
  }

  public static RoutineRepositoryImpl_Factory create(Provider<RoutineDao> routineDaoProvider,
      Provider<WorkoutDayDao> workoutDayDaoProvider, Provider<ExerciseDao> exerciseDaoProvider) {
    return new RoutineRepositoryImpl_Factory(routineDaoProvider, workoutDayDaoProvider, exerciseDaoProvider);
  }

  public static RoutineRepositoryImpl newInstance(RoutineDao routineDao,
      WorkoutDayDao workoutDayDao, ExerciseDao exerciseDao) {
    return new RoutineRepositoryImpl(routineDao, workoutDayDao, exerciseDao);
  }
}
