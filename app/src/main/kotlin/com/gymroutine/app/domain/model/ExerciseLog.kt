package com.gymroutine.app.domain.model

data class ExerciseLog(
    val id: Long = 0,
    val workoutSessionId: Long,
    val exerciseId: Long,
    val exerciseName: String = "",
    val setNumber: Int,
    val weight: Float,
    val reps: Int
)
