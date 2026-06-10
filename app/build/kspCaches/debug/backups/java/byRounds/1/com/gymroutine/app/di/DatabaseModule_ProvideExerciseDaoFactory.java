package com.gymroutine.app.di;

import com.gymroutine.app.data.local.dao.ExerciseDao;
import com.gymroutine.app.data.local.database.GymRoutineDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideExerciseDaoFactory implements Factory<ExerciseDao> {
  private final Provider<GymRoutineDatabase> dbProvider;

  private DatabaseModule_ProvideExerciseDaoFactory(Provider<GymRoutineDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ExerciseDao get() {
    return provideExerciseDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideExerciseDaoFactory create(
      Provider<GymRoutineDatabase> dbProvider) {
    return new DatabaseModule_ProvideExerciseDaoFactory(dbProvider);
  }

  public static ExerciseDao provideExerciseDao(GymRoutineDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExerciseDao(db));
  }
}
