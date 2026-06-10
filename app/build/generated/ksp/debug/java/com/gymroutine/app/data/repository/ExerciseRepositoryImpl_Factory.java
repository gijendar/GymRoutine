package com.gymroutine.app.data.repository;

import com.gymroutine.app.data.local.dao.ExerciseDao;
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
public final class ExerciseRepositoryImpl_Factory implements Factory<ExerciseRepositoryImpl> {
  private final Provider<ExerciseDao> exerciseDaoProvider;

  private ExerciseRepositoryImpl_Factory(Provider<ExerciseDao> exerciseDaoProvider) {
    this.exerciseDaoProvider = exerciseDaoProvider;
  }

  @Override
  public ExerciseRepositoryImpl get() {
    return newInstance(exerciseDaoProvider.get());
  }

  public static ExerciseRepositoryImpl_Factory create(Provider<ExerciseDao> exerciseDaoProvider) {
    return new ExerciseRepositoryImpl_Factory(exerciseDaoProvider);
  }

  public static ExerciseRepositoryImpl newInstance(ExerciseDao exerciseDao) {
    return new ExerciseRepositoryImpl(exerciseDao);
  }
}
