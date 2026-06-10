package com.gymroutine.app.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "workout_sessions",
    foreignKeys = [ForeignKey(
        entity = WorkoutDayEntity::class,
        parentColumns = ["id"],
        childColumns = ["workoutDayId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("workoutDayId")]
)
data class WorkoutSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workoutDayId: Long,
    val sessionDate: Long = System.currentTimeMillis(),
    val startTime: Long = System.currentTimeMillis(),
    val endTime: Long? = null
)
