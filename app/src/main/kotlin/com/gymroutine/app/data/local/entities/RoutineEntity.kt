package com.gymroutine.app.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class RoutineEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val routineName: String,
    val createdAt: Long = System.currentTimeMillis()
)
