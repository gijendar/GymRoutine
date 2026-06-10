package com.gymroutine.app.di;

import com.gymroutine.app.data.local.dao.ExerciseLogDao;
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
public final class DatabaseModule_ProvideExerciseLogDaoFactory implements Factory<ExerciseLogDao> {
  private final Provider<GymRoutineDatabase> dbProvider;

  private DatabaseModule_ProvideExerciseLogDaoFactory(Provider<GymRoutineDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ExerciseLogDao get() {
    return provideExerciseLogDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideExerciseLogDaoFactory create(
      Provider<GymRoutineDatabase> dbProvider) {
    return new DatabaseModule_ProvideExerciseLogDaoFactory(dbProvider);
  }

  public static ExerciseLogDao provideExerciseLogDao(GymRoutineDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExerciseLogDao(db));
  }
}
