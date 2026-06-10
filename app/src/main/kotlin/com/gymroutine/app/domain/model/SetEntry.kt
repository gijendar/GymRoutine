package com.gymroutine.app.domain.model

data class SetEntry(
    val setNumber: Int,
    val weight: String = "",
    val reps: String = "",
    val isCompleted: Boolean = false
)
