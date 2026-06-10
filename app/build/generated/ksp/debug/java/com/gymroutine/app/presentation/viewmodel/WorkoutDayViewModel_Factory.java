package com.gymroutine.app.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.gymroutine.app.domain.repository.ExerciseRepository;
import com.gymroutine.app.domain.repository.WorkoutDayRepository;
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
public final class WorkoutDayViewModel_Factory implements Factory<WorkoutDayViewModel> {
  private final Provider<WorkoutDayRepository> workoutDayRepositoryProvider;

  private final Provider<ExerciseRepository> exerciseRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private WorkoutDayViewModel_Factory(Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.workoutDayRepositoryProvider = workoutDayRepositoryProvider;
    this.exerciseRepositoryProvider = exerciseRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public WorkoutDayViewModel get() {
    return newInstance(workoutDayRepositoryProvider.get(), exerciseRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static WorkoutDayViewModel_Factory create(
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new WorkoutDayViewModel_Factory(workoutDayRepositoryProvider, exerciseRepositoryProvider, savedStateHandleProvider);
  }

  public static WorkoutDayViewModel newInstance(WorkoutDayRepository workoutDayRepository,
      ExerciseRepository exerciseRepository, SavedStateHandle savedStateHandle) {
    return new WorkoutDayViewModel(workoutDayRepository, exerciseRepository, savedStateHandle);
  }
}
