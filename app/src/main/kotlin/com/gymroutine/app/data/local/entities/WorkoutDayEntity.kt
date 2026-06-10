package com.gymroutine.app.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Represents an Exercise Category inside a Routine.
 * e.g. "Chest", "Back", "Legs", "Shoulders"
 * Table name kept as workout_days for DB migration compatibility.
 */
@Entity(
    tableName = "workout_days",
    foreignKeys = [ForeignKey(
        entity = RoutineEntity::class,
        parentColumns = ["id"],
        childColumns = ["routineId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("routineId")]
)
data class WorkoutDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val routineId: Long,
    val dayName: String,        // stores category name e.g. "Chest"
    val orderIndex: Int = 0
)
