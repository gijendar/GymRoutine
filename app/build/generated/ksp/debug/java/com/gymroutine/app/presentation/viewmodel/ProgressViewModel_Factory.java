package com.gymroutine.app.presentation.viewmodel;

import com.gymroutine.app.domain.repository.ExerciseLogRepository;
import com.gymroutine.app.domain.repository.RoutineRepository;
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
public final class ProgressViewModel_Factory implements Factory<ProgressViewModel> {
  private final Provider<WorkoutSessionRepository> sessionRepositoryProvider;

  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<ExerciseLogRepository> exerciseLogRepositoryProvider;

  private ProgressViewModel_Factory(Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<ExerciseLogRepository> exerciseLogRepositoryProvider) {
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.exerciseLogRepositoryProvider = exerciseLogRepositoryProvider;
  }

  @Override
  public ProgressViewModel get() {
    return newInstance(sessionRepositoryProvider.get(), routineRepositoryProvider.get(), exerciseLogRepositoryProvider.get());
  }

  public static ProgressViewModel_Factory create(
      Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<ExerciseLogRepository> exerciseLogRepositoryProvider) {
    return new ProgressViewModel_Factory(sessionRepositoryProvider, routineRepositoryProvider, exerciseLogRepositoryProvider);
  }

  public static ProgressViewModel newInstance(WorkoutSessionRepository sessionRepository,
      RoutineRepository routineRepository, ExerciseLogRepository exerciseLogRepository) {
    return new ProgressViewModel(sessionRepository, routineRepository, exerciseLogRepository);
  }
}
