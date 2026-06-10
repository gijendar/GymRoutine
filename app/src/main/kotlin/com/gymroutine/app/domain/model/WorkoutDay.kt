package com.gymroutine.app.domain.model

/**
 * Domain model for an Exercise Category within a Routine.
 * Examples: Chest, Back, Legs, Shoulders, Arms, Upper Body, Power Day
 */
data class WorkoutDay(
    val id: Long = 0,
    val routineId: Long,
    val name: String,               // category name
    val orderIndex: Int = 0,
    val exerciseCount: Int = 0
)
