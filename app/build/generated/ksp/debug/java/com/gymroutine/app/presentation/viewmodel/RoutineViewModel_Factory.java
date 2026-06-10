package com.gymroutine.app.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.gymroutine.app.domain.repository.RoutineRepository;
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
public final class RoutineViewModel_Factory implements Factory<RoutineViewModel> {
  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<WorkoutDayRepository> workoutDayRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private RoutineViewModel_Factory(Provider<RoutineRepository> routineRepositoryProvider,
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.workoutDayRepositoryProvider = workoutDayRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public RoutineViewModel get() {
    return newInstance(routineRepositoryProvider.get(), workoutDayRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static RoutineViewModel_Factory create(
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new RoutineViewModel_Factory(routineRepositoryProvider, workoutDayRepositoryProvider, savedStateHandleProvider);
  }

  public static RoutineViewModel newInstance(RoutineRepository routineRepository,
      WorkoutDayRepository workoutDayRepository, SavedStateHandle savedStateHandle) {
    return new RoutineViewModel(routineRepository, workoutDayRepository, savedStateHandle);
  }
}
