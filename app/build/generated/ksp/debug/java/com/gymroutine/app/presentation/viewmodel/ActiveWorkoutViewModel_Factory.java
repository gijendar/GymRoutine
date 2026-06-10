package com.gymroutine.app.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.gymroutine.app.domain.repository.ExerciseLogRepository;
import com.gymroutine.app.domain.repository.ExerciseRepository;
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
public final class ActiveWorkoutViewModel_Factory implements Factory<ActiveWorkoutViewModel> {
  private final Provider<WorkoutDayRepository> workoutDayRepositoryProvider;

  private final Provider<ExerciseRepository> exerciseRepositoryProvider;

  private final Provider<WorkoutSessionRepository> sessionRepositoryProvider;

  private final Provider<ExerciseLogRepository> exerciseLogRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private ActiveWorkoutViewModel_Factory(
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<ExerciseLogRepository> exerciseLogRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.workoutDayRepositoryProvider = workoutDayRepositoryProvider;
    this.exerciseRepositoryProvider = exerciseRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.exerciseLogRepositoryProvider = exerciseLogRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ActiveWorkoutViewModel get() {
    return newInstance(workoutDayRepositoryProvider.get(), exerciseRepositoryProvider.get(), sessionRepositoryProvider.get(), exerciseLogRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static ActiveWorkoutViewModel_Factory create(
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<ExerciseLogRepository> exerciseLogRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ActiveWorkoutViewModel_Factory(workoutDayRepositoryProvider, exerciseRepositoryProvider, sessionRepositoryProvider, exerciseLogRepositoryProvider, savedStateHandleProvider);
  }

  public static ActiveWorkoutViewModel newInstance(WorkoutDayRepository workoutDayRepository,
      ExerciseRepository exerciseRepository, WorkoutSessionRepository sessionRepository,
      ExerciseLogRepository exerciseLogRepository, SavedStateHandle savedStateHandle) {
    return new ActiveWorkoutViewModel(workoutDayRepository, exerciseRepository, sessionRepository, exerciseLogRepository, savedStateHandle);
  }
}
