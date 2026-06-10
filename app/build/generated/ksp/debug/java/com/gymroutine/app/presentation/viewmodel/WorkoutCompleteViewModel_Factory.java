package com.gymroutine.app.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.gymroutine.app.domain.repository.ExerciseLogRepository;
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
public final class WorkoutCompleteViewModel_Factory implements Factory<WorkoutCompleteViewModel> {
  private final Provider<WorkoutSessionRepository> sessionRepositoryProvider;

  private final Provider<WorkoutDayRepository> workoutDayRepositoryProvider;

  private final Provider<ExerciseLogRepository> exerciseLogRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private WorkoutCompleteViewModel_Factory(
      Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<ExerciseLogRepository> exerciseLogRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.workoutDayRepositoryProvider = workoutDayRepositoryProvider;
    this.exerciseLogRepositoryProvider = exerciseLogRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public WorkoutCompleteViewModel get() {
    return newInstance(sessionRepositoryProvider.get(), workoutDayRepositoryProvider.get(), exerciseLogRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static WorkoutCompleteViewModel_Factory create(
      Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<ExerciseLogRepository> exerciseLogRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new WorkoutCompleteViewModel_Factory(sessionRepositoryProvider, workoutDayRepositoryProvider, exerciseLogRepositoryProvider, savedStateHandleProvider);
  }

  public static WorkoutCompleteViewModel newInstance(WorkoutSessionRepository sessionRepository,
      WorkoutDayRepository workoutDayRepository, ExerciseLogRepository exerciseLogRepository,
      SavedStateHandle savedStateHandle) {
    return new WorkoutCompleteViewModel(sessionRepository, workoutDayRepository, exerciseLogRepository, savedStateHandle);
  }
}
