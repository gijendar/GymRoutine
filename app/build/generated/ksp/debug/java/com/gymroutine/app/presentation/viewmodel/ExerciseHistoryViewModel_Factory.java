package com.gymroutine.app.presentation.viewmodel;

import androidx.lifecycle.SavedStateHandle;
import com.gymroutine.app.domain.repository.ExerciseLogRepository;
import com.gymroutine.app.domain.repository.ExerciseRepository;
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
public final class ExerciseHistoryViewModel_Factory implements Factory<ExerciseHistoryViewModel> {
  private final Provider<ExerciseRepository> exerciseRepositoryProvider;

  private final Provider<ExerciseLogRepository> exerciseLogRepositoryProvider;

  private final Provider<WorkoutSessionRepository> sessionRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private ExerciseHistoryViewModel_Factory(Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<ExerciseLogRepository> exerciseLogRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.exerciseRepositoryProvider = exerciseRepositoryProvider;
    this.exerciseLogRepositoryProvider = exerciseLogRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ExerciseHistoryViewModel get() {
    return newInstance(exerciseRepositoryProvider.get(), exerciseLogRepositoryProvider.get(), sessionRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static ExerciseHistoryViewModel_Factory create(
      Provider<ExerciseRepository> exerciseRepositoryProvider,
      Provider<ExerciseLogRepository> exerciseLogRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ExerciseHistoryViewModel_Factory(exerciseRepositoryProvider, exerciseLogRepositoryProvider, sessionRepositoryProvider, savedStateHandleProvider);
  }

  public static ExerciseHistoryViewModel newInstance(ExerciseRepository exerciseRepository,
      ExerciseLogRepository exerciseLogRepository, WorkoutSessionRepository sessionRepository,
      SavedStateHandle savedStateHandle) {
    return new ExerciseHistoryViewModel(exerciseRepository, exerciseLogRepository, sessionRepository, savedStateHandle);
  }
}
