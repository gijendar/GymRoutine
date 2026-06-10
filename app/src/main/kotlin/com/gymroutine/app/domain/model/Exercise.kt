package com.gymroutine.app.domain.model

data class Exercise(
    val id: Long = 0,
    val workoutDayId: Long,
    val name: String,
    val orderIndex: Int = 0
)
