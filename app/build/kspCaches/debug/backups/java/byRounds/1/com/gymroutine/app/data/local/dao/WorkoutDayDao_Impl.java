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
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.gymroutine.app.data.local.entities.WorkoutDayEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class WorkoutDayDao_Impl implements WorkoutDayDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkoutDayEntity> __insertionAdapterOfWorkoutDayEntity;

  private final EntityDeletionOrUpdateAdapter<WorkoutDayEntity> __deletionAdapterOfWorkoutDayEntity;

  private final EntityDeletionOrUpdateAdapter<WorkoutDayEntity> __updateAdapterOfWorkoutDayEntity;

  public WorkoutDayDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkoutDayEntity = new EntityInsertionAdapter<WorkoutDayEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `workout_days` (`id`,`routineId`,`dayName`,`orderIndex`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutDayEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRoutineId());
        statement.bindString(3, entity.getDayName());
        statement.bindLong(4, entity.getOrderIndex());
      }
    };
    this.__deletionAdapterOfWorkoutDayEntity = new EntityDeletionOrUpdateAdapter<WorkoutDayEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `workout_days` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutDayEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfWorkoutDayEntity = new EntityDeletionOrUpdateAdapter<WorkoutDayEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `workout_days` SET `id` = ?,`routineId` = ?,`dayName` = ?,`orderIndex` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkoutDayEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getRoutineId());
        statement.bindString(3, entity.getDayName());
        statement.bindLong(4, entity.getOrderIndex());
        statement.bindLong(5, entity.getId());
      }
    };
  }

  @Override
  public Object insertDay(final WorkoutDayEntity day,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWorkoutDayEntity.insertAndReturnId(day);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDay(final WorkoutDayEntity day,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWorkoutDayEntity.handle(day);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDay(final WorkoutDayEntity day,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWorkoutDayEntity.handle(day);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WorkoutDayEntity>> getDaysForRoutine(final long routineId) {
    final String _sql = "SELECT * FROM workout_days WHERE routineId = ? ORDER BY orderIndex ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, routineId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workout_days"}, new Callable<List<WorkoutDayEntity>>() {
      @Override
      @NonNull
      public List<WorkoutDayEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoutineId = CursorUtil.getColumnIndexOrThrow(_cursor, "routineId");
          final int _cursorIndexOfDayName = CursorUtil.getColumnIndexOrThrow(_cursor, "dayName");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final List<WorkoutDayEntity> _result = new ArrayList<WorkoutDayEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutDayEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpRoutineId;
            _tmpRoutineId = _cursor.getLong(_cursorIndexOfRoutineId);
            final String _tmpDayName;
            _tmpDayName = _cursor.getString(_cursorIndexOfDayName);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            _item = new WorkoutDayEntity(_tmpId,_tmpRoutineId,_tmpDayName,_tmpOrderIndex);
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
  public Object getDayById(final long id,
      final Continuation<? super WorkoutDayEntity> $completion) {
    final String _sql = "SELECT * FROM workout_days WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WorkoutDayEntity>() {
      @Override
      @Nullable
      public WorkoutDayEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoutineId = CursorUtil.getColumnIndexOrThrow(_cursor, "routineId");
          final int _cursorIndexOfDayName = CursorUtil.getColumnIndexOrThrow(_cursor, "dayName");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final WorkoutDayEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpRoutineId;
            _tmpRoutineId = _cursor.getLong(_cursorIndexOfRoutineId);
            final String _tmpDayName;
            _tmpDayName = _cursor.getString(_cursorIndexOfDayName);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            _result = new WorkoutDayEntity(_tmpId,_tmpRoutineId,_tmpDayName,_tmpOrderIndex);
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
  public Flow<List<WorkoutDayEntity>> searchDays(final String query) {
    final String _sql = "SELECT * FROM workout_days WHERE dayName LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workout_days"}, new Callable<List<WorkoutDayEntity>>() {
      @Override
      @NonNull
      public List<WorkoutDayEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoutineId = CursorUtil.getColumnIndexOrThrow(_cursor, "routineId");
          final int _cursorIndexOfDayName = CursorUtil.getColumnIndexOrThrow(_cursor, "dayName");
          final int _cursorIndexOfOrderIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "orderIndex");
          final List<WorkoutDayEntity> _result = new ArrayList<WorkoutDayEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkoutDayEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpRoutineId;
            _tmpRoutineId = _cursor.getLong(_cursorIndexOfRoutineId);
            final String _tmpDayName;
            _tmpDayName = _cursor.getString(_cursorIndexOfDayName);
            final int _tmpOrderIndex;
            _tmpOrderIndex = _cursor.getInt(_cursorIndexOfOrderIndex);
            _item = new WorkoutDayEntity(_tmpId,_tmpRoutineId,_tmpDayName,_tmpOrderIndex);
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
  public Object getDayCountForRoutine(final long routineId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM workout_days WHERE routineId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, routineId);
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
