package com.gymroutine.app.presentation.viewmodel;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<WorkoutSessionRepository> sessionRepositoryProvider;

  private HomeViewModel_Factory(Provider<RoutineRepository> routineRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider) {
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.sessionRepositoryProvider = sessionRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(routineRepositoryProvider.get(), sessionRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<RoutineRepository> routineRepositoryProvider,
      Provider<WorkoutSessionRepository> sessionRepositoryProvider) {
    return new HomeViewModel_Factory(routineRepositoryProvider, sessionRepositoryProvider);
  }

  public static HomeViewModel newInstance(RoutineRepository routineRepository,
      WorkoutSessionRepository sessionRepository) {
    return new HomeViewModel(routineRepository, sessionRepository);
  }
}
