package com.gymroutine.app.data.repository;

import com.gymroutine.app.data.local.dao.WorkoutSessionDao;
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
public final class WorkoutSessionRepositoryImpl_Factory implements Factory<WorkoutSessionRepositoryImpl> {
  private final Provider<WorkoutSessionDao> workoutSessionDaoProvider;

  private WorkoutSessionRepositoryImpl_Factory(
      Provider<WorkoutSessionDao> workoutSessionDaoProvider) {
    this.workoutSessionDaoProvider = workoutSessionDaoProvider;
  }

  @Override
  public WorkoutSessionRepositoryImpl get() {
    return newInstance(workoutSessionDaoProvider.get());
  }

  public static WorkoutSessionRepositoryImpl_Factory create(
      Provider<WorkoutSessionDao> workoutSessionDaoProvider) {
    return new WorkoutSessionRepositoryImpl_Factory(workoutSessionDaoProvider);
  }

  public static WorkoutSessionRepositoryImpl newInstance(WorkoutSessionDao workoutSessionDao) {
    return new WorkoutSessionRepositoryImpl(workoutSessionDao);
  }
}
