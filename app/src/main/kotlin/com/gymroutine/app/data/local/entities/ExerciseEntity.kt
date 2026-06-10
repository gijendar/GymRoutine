package com.gymroutine.app.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "exercises",
    foreignKeys = [ForeignKey(
        entity = WorkoutDayEntity::class,
        parentColumns = ["id"],
        childColumns = ["workoutDayId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("workoutDayId")]
)
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val workoutDayId: Long,
    val exerciseName: String,
    val orderIndex: Int = 0
)
