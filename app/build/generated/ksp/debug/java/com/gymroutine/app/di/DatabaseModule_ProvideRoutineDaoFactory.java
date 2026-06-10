package com.gymroutine.app.di;

import com.gymroutine.app.data.local.dao.RoutineDao;
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
public final class DatabaseModule_ProvideRoutineDaoFactory implements Factory<RoutineDao> {
  private final Provider<GymRoutineDatabase> dbProvider;

  private DatabaseModule_ProvideRoutineDaoFactory(Provider<GymRoutineDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public RoutineDao get() {
    return provideRoutineDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideRoutineDaoFactory create(
      Provider<GymRoutineDatabase> dbProvider) {
    return new DatabaseModule_ProvideRoutineDaoFactory(dbProvider);
  }

  public static RoutineDao provideRoutineDao(GymRoutineDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRoutineDao(db));
  }
}
