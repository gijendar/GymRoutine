package com.gymroutine.app.presentation.viewmodel;

import com.gymroutine.app.domain.repository.RoutineRepository;
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
public final class CreateRoutineViewModel_Factory implements Factory<CreateRoutineViewModel> {
  private final Provider<RoutineRepository> routineRepositoryProvider;

  private CreateRoutineViewModel_Factory(Provider<RoutineRepository> routineRepositoryProvider) {
    this.routineRepositoryProvider = routineRepositoryProvider;
  }

  @Override
  public CreateRoutineViewModel get() {
    return newInstance(routineRepositoryProvider.get());
  }

  public static CreateRoutineViewModel_Factory create(
      Provider<RoutineRepository> routineRepositoryProvider) {
    return new CreateRoutineViewModel_Factory(routineRepositoryProvider);
  }

  public static CreateRoutineViewModel newInstance(RoutineRepository routineRepository) {
    return new CreateRoutineViewModel(routineRepository);
  }
}
