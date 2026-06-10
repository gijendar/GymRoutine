package com.gymroutine.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.gymroutine.app.data.local.entities.ExerciseLogEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ExerciseLogDao_Impl implements ExerciseLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExerciseLogEntity> __insertionAdapterOfExerciseLogEntity;

  private final EntityDeletionOrUpdateAdapter<ExerciseLogEntity> __deletionAdapterOfExerciseLogEntity;

  private final EntityDeletionOrUpdateAdapter<ExerciseLogEntity> __updateAdapterOfExerciseLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLogsForExerciseInSession;

  public ExerciseLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExerciseLogEntity = new EntityInsertionAdapter<ExerciseLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `exercise_logs` (`id`,`workoutSessionId`,`exerciseId`,`setNumber`,`weight`,`reps`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExerciseLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getWorkoutSessionId());
        statement.bindLong(3, entity.getExerciseId());
        statement.bindLong(4, entity.getSetNumber());
        statement.bindDouble(5, entity.getWeight());
        statement.bindLong(6, entity.getReps());
      }
    };
    this.__deletionAdapterOfExerciseLogEntity = new EntityDeletionOrUpdateAdapter<ExerciseLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `exercise_logs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExerciseLogEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfExerciseLogEntity = new EntityDeletionOrUpdateAdapter<ExerciseLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `exercise_logs` SET `id` = ?,`workoutSessionId` = ?,`exerciseId` = ?,`setNumber` = ?,`weight` = ?,`reps` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExerciseLogEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getWorkoutSessionId());
        statement.bindLong(3, entity.getExerciseId());
        statement.bindLong(4, entity.getSetNumber());
        statement.bindDouble(5, entity.getWeight());
        statement.bindLong(6, entity.getReps());
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteLogsForExerciseInSession = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM exercise_logs WHERE workoutSessionId = ? AND exerciseId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertLog(final ExerciseLogEntity log,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfExerciseLogEntity.insertAndReturnId(log);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLogs(final List<ExerciseLogEntity> logs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfExerciseLogEntity.insert(logs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLog(final ExerciseLogEntity log,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfExerciseLogEntity.handle(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLog(final ExerciseLogEntity log,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfExerciseLogEntity.handle(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLogsForExerciseInSession(final long sessionId, final long exerciseId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLogsForExerciseInSession.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, sessionId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, exerciseId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteLogsForExerciseInSession.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ExerciseLogEntity>> getLogsForSession(final long sessionId) {
    final String _sql = "SELECT * FROM exercise_logs WHERE workoutSessionId = ? ORDER BY setNumber ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sessionId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercise_logs"}, new Callable<List<ExerciseLogEntity>>() {
      @Override
      @NonNull
      public List<ExerciseLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWorkoutSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutSessionId");
          final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
          final int _cursorIndexOfSetNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "setNumber");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final List<ExerciseLogEntity> _result = new ArrayList<ExerciseLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExerciseLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWorkoutSessionId;
            _tmpWorkoutSessionId = _cursor.getLong(_cursorIndexOfWorkoutSessionId);
            final long _tmpExerciseId;
            _tmpExerciseId = _cursor.getLong(_cursorIndexOfExerciseId);
            final int _tmpSetNumber;
            _tmpSetNumber = _cursor.getInt(_cursorIndexOfSetNumber);
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            final int _tmpReps;
            _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            _item = new ExerciseLogEntity(_tmpId,_tmpWorkoutSessionId,_tmpExerciseId,_tmpSetNumber,_tmpWeight,_tmpReps);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ExerciseLogEntity>> getLogsForExercise(final long exerciseId) {
    final String _sql = "SELECT * FROM exercise_logs WHERE exerciseId = ? ORDER BY workoutSessionId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, exerciseId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercise_logs"}, new Callable<List<ExerciseLogEntity>>() {
      @Override
      @NonNull
      public List<ExerciseLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWorkoutSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutSessionId");
          final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
          final int _cursorIndexOfSetNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "setNumber");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final List<ExerciseLogEntity> _result = new ArrayList<ExerciseLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExerciseLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWorkoutSessionId;
            _tmpWorkoutSessionId = _cursor.getLong(_cursorIndexOfWorkoutSessionId);
            final long _tmpExerciseId;
            _tmpExerciseId = _cursor.getLong(_cursorIndexOfExerciseId);
            final int _tmpSetNumber;
            _tmpSetNumber = _cursor.getInt(_cursorIndexOfSetNumber);
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            final int _tmpReps;
            _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            _item = new ExerciseLogEntity(_tmpId,_tmpWorkoutSessionId,_tmpExerciseId,_tmpSetNumber,_tmpWeight,_tmpReps);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ExerciseLogEntity>> getLogsForExerciseInSession(final long exerciseId,
      final long sessionId) {
    final String _sql = "SELECT * FROM exercise_logs WHERE exerciseId = ? AND workoutSessionId = ? ORDER BY setNumber ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, exerciseId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, sessionId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exercise_logs"}, new Callable<List<ExerciseLogEntity>>() {
      @Override
      @NonNull
      public List<ExerciseLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWorkoutSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutSessionId");
          final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
          final int _cursorIndexOfSetNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "setNumber");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final List<ExerciseLogEntity> _result = new ArrayList<ExerciseLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExerciseLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWorkoutSessionId;
            _tmpWorkoutSessionId = _cursor.getLong(_cursorIndexOfWorkoutSessionId);
            final long _tmpExerciseId;
            _tmpExerciseId = _cursor.getLong(_cursorIndexOfExerciseId);
            final int _tmpSetNumber;
            _tmpSetNumber = _cursor.getInt(_cursorIndexOfSetNumber);
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            final int _tmpReps;
            _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            _item = new ExerciseLogEntity(_tmpId,_tmpWorkoutSessionId,_tmpExerciseId,_tmpSetNumber,_tmpWeight,_tmpReps);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getLastLogForExercise(final long exerciseId,
      final Continuation<? super ExerciseLogEntity> $completion) {
    final String _sql = "\n"
            + "        SELECT el.* FROM exercise_logs el\n"
            + "        INNER JOIN workout_sessions ws ON el.workoutSessionId = ws.id\n"
            + "        WHERE el.exerciseId = ?\n"
            + "        ORDER BY ws.sessionDate DESC\n"
            + "        LIMIT 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, exerciseId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExerciseLogEntity>() {
      @Override
      @Nullable
      public ExerciseLogEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfWorkoutSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutSessionId");
          final int _cursorIndexOfExerciseId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
          final int _cursorIndexOfSetNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "setNumber");
          final int _cursorIndexOfWeight = CursorUtil.getColumnIndexOrThrow(_cursor, "weight");
          final int _cursorIndexOfReps = CursorUtil.getColumnIndexOrThrow(_cursor, "reps");
          final ExerciseLogEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpWorkoutSessionId;
            _tmpWorkoutSessionId = _cursor.getLong(_cursorIndexOfWorkoutSessionId);
            final long _tmpExerciseId;
            _tmpExerciseId = _cursor.getLong(_cursorIndexOfExerciseId);
            final int _tmpSetNumber;
            _tmpSetNumber = _cursor.getInt(_cursorIndexOfSetNumber);
            final float _tmpWeight;
            _tmpWeight = _cursor.getFloat(_cursorIndexOfWeight);
            final int _tmpReps;
            _tmpReps = _cursor.getInt(_cursorIndexOfReps);
            _result = new ExerciseLogEntity(_tmpId,_tmpWorkoutSessionId,_tmpExerciseId,_tmpSetNumber,_tmpWeight,_tmpReps);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getBestWeightForExercise(final long exerciseId,
      final Continuation<? super Float> $completion) {
    final String _sql = "\n"
            + "        SELECT MAX(weight) FROM exercise_logs WHERE exerciseId = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, exerciseId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getBestRepsForExercise(final long exerciseId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "\n"
            + "        SELECT MAX(reps) FROM exercise_logs WHERE exerciseId = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, exerciseId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalVolumeForSession(final long sessionId,
      final Continuation<? super Float> $completion) {
    final String _sql = "\n"
            + "        SELECT SUM(weight * reps) FROM exercise_logs\n"
            + "        WHERE workoutSessionId = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sessionId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSessionCountForExercise(final long exerciseId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(DISTINCT workoutSessionId) FROM exercise_logs WHERE exerciseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, exerciseId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
