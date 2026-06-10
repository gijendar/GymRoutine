package com.gymroutine.app.data.repository;

import com.gymroutine.app.data.local.dao.ExerciseLogDao;
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
public final class ExerciseLogRepositoryImpl_Factory implements Factory<ExerciseLogRepositoryImpl> {
  private final Provider<ExerciseLogDao> exerciseLogDaoProvider;

  private ExerciseLogRepositoryImpl_Factory(Provider<ExerciseLogDao> exerciseLogDaoProvider) {
    this.exerciseLogDaoProvider = exerciseLogDaoProvider;
  }

  @Override
  public ExerciseLogRepositoryImpl get() {
    return newInstance(exerciseLogDaoProvider.get());
  }

  public static ExerciseLogRepositoryImpl_Factory create(
      Provider<ExerciseLogDao> exerciseLogDaoProvider) {
    return new ExerciseLogRepositoryImpl_Factory(exerciseLogDaoProvider);
  }

  public static ExerciseLogRepositoryImpl newInstance(ExerciseLogDao exerciseLogDao) {
    return new ExerciseLogRepositoryImpl(exerciseLogDao);
  }
}
