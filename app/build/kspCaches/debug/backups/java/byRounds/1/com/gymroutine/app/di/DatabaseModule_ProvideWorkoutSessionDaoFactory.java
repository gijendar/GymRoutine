package com.gymroutine.app.di;

import com.gymroutine.app.data.local.dao.WorkoutSessionDao;
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
public final class DatabaseModule_ProvideWorkoutSessionDaoFactory implements Factory<WorkoutSessionDao> {
  private final Provider<GymRoutineDatabase> dbProvider;

  private DatabaseModule_ProvideWorkoutSessionDaoFactory(Provider<GymRoutineDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public WorkoutSessionDao get() {
    return provideWorkoutSessionDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideWorkoutSessionDaoFactory create(
      Provider<GymRoutineDatabase> dbProvider) {
    return new DatabaseModule_ProvideWorkoutSessionDaoFactory(dbProvider);
  }

  public static WorkoutSessionDao provideWorkoutSessionDao(GymRoutineDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideWorkoutSessionDao(db));
  }
}
