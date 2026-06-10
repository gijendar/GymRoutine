package com.gymroutine.app.di;

import com.gymroutine.app.data.local.dao.WorkoutDayDao;
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
public final class DatabaseModule_ProvideWorkoutDayDaoFactory implements Factory<WorkoutDayDao> {
  private final Provider<GymRoutineDatabase> dbProvider;

  private DatabaseModule_ProvideWorkoutDayDaoFactory(Provider<GymRoutineDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public WorkoutDayDao get() {
    return provideWorkoutDayDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideWorkoutDayDaoFactory create(
      Provider<GymRoutineDatabase> dbProvider) {
    return new DatabaseModule_ProvideWorkoutDayDaoFactory(dbProvider);
  }

  public static WorkoutDayDao provideWorkoutDayDao(GymRoutineDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideWorkoutDayDao(db));
  }
}
