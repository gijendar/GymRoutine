package com.gymroutine.app.domain.model

data class WorkoutSession(
    val id: Long = 0,
    val workoutDayId: Long,
    val sessionDate: Long = System.currentTimeMillis(),
    val startTime: Long = System.currentTimeMillis(),
    val endTime: Long? = null,
    val dayName: String = "",
    val totalSets: Int = 0,
    val totalVolume: Float = 0f
)
