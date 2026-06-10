package com.gymroutine.app.presentation.viewmodel;

import com.gymroutine.app.domain.repository.ExerciseRepository;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<RoutineRepository> routineRepositoryProvider;

  private final Provider<WorkoutDayRepository> workoutDayRepositoryProvider;

  private final Provider<ExerciseRepository> exerciseRepositoryProvider;

  private SearchViewModel_Factory(Provider<RoutineRepository> routineRepositoryProvider,
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<ExerciseRepository> exerciseRepositoryProvider) {
    this.routineRepositoryProvider = routineRepositoryProvider;
    this.workoutDayRepositoryProvider = workoutDayRepositoryProvider;
    this.exerciseRepositoryProvider = exerciseRepositoryProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(routineRepositoryProvider.get(), workoutDayRepositoryProvider.get(), exerciseRepositoryProvider.get());
  }

  public static SearchViewModel_Factory create(
      Provider<RoutineRepository> routineRepositoryProvider,
      Provider<WorkoutDayRepository> workoutDayRepositoryProvider,
      Provider<ExerciseRepository> exerciseRepositoryProvider) {
    return new SearchViewModel_Factory(routineRepositoryProvider, workoutDayRepositoryProvider, exerciseRepositoryProvider);
  }

  public static SearchViewModel newInstance(RoutineRepository routineRepository,
      WorkoutDayRepository workoutDayRepository, ExerciseRepository exerciseRepository) {
    return new SearchViewModel(routineRepository, workoutDayRepository, exerciseRepository);
  }
}
