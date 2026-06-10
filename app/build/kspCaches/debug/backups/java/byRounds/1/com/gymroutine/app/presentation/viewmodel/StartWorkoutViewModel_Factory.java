package com.gymroutine.app.presentation.viewmodel;

import com.gymroutine.app.domain.repository.RoutineRepository;
import com.gymroutine.app.domain.repository.WorkoutDayRepository;
import com.gymroutine.app.domain.repository.WorkoutSessionRepository;
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
public final class StartWorkoutViewModel_Factory implements Factory<StartWorkoutViewModel> {
  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<WorkoutDayRepository> workoutDayRepositoryProvider;

  private final Provider<WorkoutSessionRepository> sessionRepositoryProvider;

  private StartWorkoutViewModel_Factory(Provider<RoutineRepository> routineRepositoryProvider,
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider) {
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.workoutDayRepositoryProvider = workoutDayRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
  }

  @Override
  public StartWorkoutViewModel get() {
    return newInstance(routineRepositoryProvider.get(), workoutDayRepositoryProvider.get(), sessionRepositoryProvider.get());
  }

  public static StartWorkoutViewModel_Factory create(
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider) {
    return new StartWorkoutViewModel_Factory(routineRepositoryProvider, workoutDayRepositoryProvider, sessionRepositoryProvider);
  }

  public static StartWorkoutViewModel newInstance(RoutineRepository routineRepository,
      WorkoutDayRepository workoutDayRepository, WorkoutSessionRepository sessionRepository) {
    return new StartWorkoutViewModel(routineRepository, workoutDayRepository, sessionRepository);
  }
}
