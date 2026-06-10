package com.gymroutine.app.data.local.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.gymroutine.app.data.local.dao.ExerciseDao;
import com.gymroutine.app.data.local.dao.ExerciseDao_Impl;
import com.gymroutine.app.data.local.dao.ExerciseLogDao;
import com.gymroutine.app.data.local.dao.ExerciseLogDao_Impl;
import com.gymroutine.app.data.local.dao.RoutineDao;
import com.gymroutine.app.data.local.dao.RoutineDao_Impl;
import com.gymroutine.app.data.local.dao.WorkoutDayDao;
import com.gymroutine.app.data.local.dao.WorkoutDayDao_Impl;
import com.gymroutine.app.data.local.dao.WorkoutSessionDao;
import com.gymroutine.app.data.local.dao.WorkoutSessionDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class GymRoutineDatabase_Impl extends GymRoutineDatabase {
  private volatile RoutineDao _routineDao;

  private volatile WorkoutDayDao _workoutDayDao;

  private volatile ExerciseDao _exerciseDao;

  private volatile WorkoutSessionDao _workoutSessionDao;

  private volatile ExerciseLogDao _exerciseLogDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `routines` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `routineName` TEXT NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `workout_days` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `routineId` INTEGER NOT NULL, `dayName` TEXT NOT NULL, `orderIndex` INTEGER NOT NULL, FOREIGN KEY(`routineId`) REFERENCES `routines`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_days_routineId` ON `workout_days` (`routineId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `exercises` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `workoutDayId` INTEGER NOT NULL, `exerciseName` TEXT NOT NULL, `orderIndex` INTEGER NOT NULL, FOREIGN KEY(`workoutDayId`) REFERENCES `workout_days`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_exercises_workoutDayId` ON `exercises` (`workoutDayId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `workout_sessions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `workoutDayId` INTEGER NOT NULL, `sessionDate` INTEGER NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER, FOREIGN KEY(`workoutDayId`) REFERENCES `workout_days`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_workout_sessions_workoutDayId` ON `workout_sessions` (`workoutDayId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `exercise_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `workoutSessionId` INTEGER NOT NULL, `exerciseId` INTEGER NOT NULL, `setNumber` INTEGER NOT NULL, `weight` REAL NOT NULL, `reps` INTEGER NOT NULL, FOREIGN KEY(`workoutSessionId`) REFERENCES `workout_sessions`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`exerciseId`) REFERENCES `exercises`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_exercise_logs_workoutSessionId` ON `exercise_logs` (`workoutSessionId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_exercise_logs_exerciseId` ON `exercise_logs` (`exerciseId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '58819d89f2f8c23db5d5aeaf562a42c2')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `routines`");
        db.execSQL("DROP TABLE IF EXISTS `workout_days`");
        db.execSQL("DROP TABLE IF EXISTS `exercises`");
        db.execSQL("DROP TABLE IF EXISTS `workout_sessions`");
        db.execSQL("DROP TABLE IF EXISTS `exercise_logs`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRoutines = new HashMap<String, TableInfo.Column>(3);
        _columnsRoutines.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutines.put("routineName", new TableInfo.Column("routineName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoutines.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRoutines = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRoutines = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRoutines = new TableInfo("routines", _columnsRoutines, _foreignKeysRoutines, _indicesRoutines);
        final TableInfo _existingRoutines = TableInfo.read(db, "routines");
        if (!_infoRoutines.equals(_existingRoutines)) {
          return new RoomOpenHelper.ValidationResult(false, "routines(com.gymroutine.app.data.local.entities.RoutineEntity).\n"
                  + " Expected:\n" + _infoRoutines + "\n"
                  + " Found:\n" + _existingRoutines);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkoutDays = new HashMap<String, TableInfo.Column>(4);
        _columnsWorkoutDays.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutDays.put("routineId", new TableInfo.Column("routineId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutDays.put("dayName", new TableInfo.Column("dayName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutDays.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkoutDays = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysWorkoutDays.add(new TableInfo.ForeignKey("routines", "CASCADE", "NO ACTION", Arrays.asList("routineId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesWorkoutDays = new HashSet<TableInfo.Index>(1);
        _indicesWorkoutDays.add(new TableInfo.Index("index_workout_days_routineId", false, Arrays.asList("routineId"), Arrays.asList("ASC")));
        final TableInfo _infoWorkoutDays = new TableInfo("workout_days", _columnsWorkoutDays, _foreignKeysWorkoutDays, _indicesWorkoutDays);
        final TableInfo _existingWorkoutDays = TableInfo.read(db, "workout_days");
        if (!_infoWorkoutDays.equals(_existingWorkoutDays)) {
          return new RoomOpenHelper.ValidationResult(false, "workout_days(com.gymroutine.app.data.local.entities.WorkoutDayEntity).\n"
                  + " Expected:\n" + _infoWorkoutDays + "\n"
                  + " Found:\n" + _existingWorkoutDays);
        }
        final HashMap<String, TableInfo.Column> _columnsExercises = new HashMap<String, TableInfo.Column>(4);
        _columnsExercises.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("workoutDayId", new TableInfo.Column("workoutDayId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("exerciseName", new TableInfo.Column("exerciseName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercises.put("orderIndex", new TableInfo.Column("orderIndex", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExercises = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysExercises.add(new TableInfo.ForeignKey("workout_days", "CASCADE", "NO ACTION", Arrays.asList("workoutDayId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesExercises = new HashSet<TableInfo.Index>(1);
        _indicesExercises.add(new TableInfo.Index("index_exercises_workoutDayId", false, Arrays.asList("workoutDayId"), Arrays.asList("ASC")));
        final TableInfo _infoExercises = new TableInfo("exercises", _columnsExercises, _foreignKeysExercises, _indicesExercises);
        final TableInfo _existingExercises = TableInfo.read(db, "exercises");
        if (!_infoExercises.equals(_existingExercises)) {
          return new RoomOpenHelper.ValidationResult(false, "exercises(com.gymroutine.app.data.local.entities.ExerciseEntity).\n"
                  + " Expected:\n" + _infoExercises + "\n"
                  + " Found:\n" + _existingExercises);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkoutSessions = new HashMap<String, TableInfo.Column>(5);
        _columnsWorkoutSessions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutSessions.put("workoutDayId", new TableInfo.Column("workoutDayId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutSessions.put("sessionDate", new TableInfo.Column("sessionDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutSessions.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkoutSessions.put("endTime", new TableInfo.Column("endTime", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkoutSessions = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysWorkoutSessions.add(new TableInfo.ForeignKey("workout_days", "CASCADE", "NO ACTION", Arrays.asList("workoutDayId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesWorkoutSessions = new HashSet<TableInfo.Index>(1);
        _indicesWorkoutSessions.add(new TableInfo.Index("index_workout_sessions_workoutDayId", false, Arrays.asList("workoutDayId"), Arrays.asList("ASC")));
        final TableInfo _infoWorkoutSessions = new TableInfo("workout_sessions", _columnsWorkoutSessions, _foreignKeysWorkoutSessions, _indicesWorkoutSessions);
        final TableInfo _existingWorkoutSessions = TableInfo.read(db, "workout_sessions");
        if (!_infoWorkoutSessions.equals(_existingWorkoutSessions)) {
          return new RoomOpenHelper.ValidationResult(false, "workout_sessions(com.gymroutine.app.data.local.entities.WorkoutSessionEntity).\n"
                  + " Expected:\n" + _infoWorkoutSessions + "\n"
                  + " Found:\n" + _existingWorkoutSessions);
        }
        final HashMap<String, TableInfo.Column> _columnsExerciseLogs = new HashMap<String, TableInfo.Column>(6);
        _columnsExerciseLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseLogs.put("workoutSessionId", new TableInfo.Column("workoutSessionId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseLogs.put("exerciseId", new TableInfo.Column("exerciseId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseLogs.put("setNumber", new TableInfo.Column("setNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseLogs.put("weight", new TableInfo.Column("weight", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExerciseLogs.put("reps", new TableInfo.Column("reps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExerciseLogs = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysExerciseLogs.add(new TableInfo.ForeignKey("workout_sessions", "CASCADE", "NO ACTION", Arrays.asList("workoutSessionId"), Arrays.asList("id")));
        _foreignKeysExerciseLogs.add(new TableInfo.ForeignKey("exercises", "CASCADE", "NO ACTION", Arrays.asList("exerciseId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesExerciseLogs = new HashSet<TableInfo.Index>(2);
        _indicesExerciseLogs.add(new TableInfo.Index("index_exercise_logs_workoutSessionId", false, Arrays.asList("workoutSessionId"), Arrays.asList("ASC")));
        _indicesExerciseLogs.add(new TableInfo.Index("index_exercise_logs_exerciseId", false, Arrays.asList("exerciseId"), Arrays.asList("ASC")));
        final TableInfo _infoExerciseLogs = new TableInfo("exercise_logs", _columnsExerciseLogs, _foreignKeysExerciseLogs, _indicesExerciseLogs);
        final TableInfo _existingExerciseLogs = TableInfo.read(db, "exercise_logs");
        if (!_infoExerciseLogs.equals(_existingExerciseLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "exercise_logs(com.gymroutine.app.data.local.entities.ExerciseLogEntity).\n"
                  + " Expected:\n" + _infoExerciseLogs + "\n"
                  + " Found:\n" + _existingExerciseLogs);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "58819d89f2f8c23db5d5aeaf562a42c2", "1b09d7717bf13a649e1e2e3f2b404aec");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "routines","workout_days","exercises","workout_sessions","exercise_logs");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `routines`");
      _db.execSQL("DELETE FROM `workout_days`");
      _db.execSQL("DELETE FROM `exercises`");
      _db.execSQL("DELETE FROM `workout_sessions`");
      _db.execSQL("DELETE FROM `exercise_logs`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RoutineDao.class, RoutineDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WorkoutDayDao.class, WorkoutDayDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExerciseDao.class, ExerciseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WorkoutSessionDao.class, WorkoutSessionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExerciseLogDao.class, ExerciseLogDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RoutineDao routineDao() {
    if (_routineDao != null) {
      return _routineDao;
    } else {
      synchronized(this) {
        if(_routineDao == null) {
          _routineDao = new RoutineDao_Impl(this);
        }
        return _routineDao;
      }
    }
  }

  @Override
  public WorkoutDayDao workoutDayDao() {
    if (_workoutDayDao != null) {
      return _workoutDayDao;
    } else {
      synchronized(this) {
        if(_workoutDayDao == null) {
          _workoutDayDao = new WorkoutDayDao_Impl(this);
        }
        return _workoutDayDao;
      }
    }
  }

  @Override
  public ExerciseDao exerciseDao() {
    if (_exerciseDao != null) {
      return _exerciseDao;
    } else {
      synchronized(this) {
        if(_exerciseDao == null) {
          _exerciseDao = new ExerciseDao_Impl(this);
        }
        return _exerciseDao;
      }
    }
  }

  @Override
  public WorkoutSessionDao workoutSessionDao() {
    if (_workoutSessionDao != null) {
      return _workoutSessionDao;
    } else {
      synchronized(this) {
        if(_workoutSessionDao == null) {
          _workoutSessionDao = new WorkoutSessionDao_Impl(this);
        }
        return _workoutSessionDao;
      }
    }
  }

  @Override
  public ExerciseLogDao exerciseLogDao() {
    if (_exerciseLogDao != null) {
      return _exerciseLogDao;
    } else {
      synchronized(this) {
        if(_exerciseLogDao == null) {
          _exerciseLogDao = new ExerciseLogDao_Impl(this);
        }
        return _exerciseLogDao;
      }
    }
  }
}
