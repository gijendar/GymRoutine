package com.gymroutine.app.domain.model

data class Routine(
    val id: Long = 0,
    val name: String,
    val createdAt: Long = System.currentTimeMillis(),
    val dayCount: Int = 0,
    val exerciseCount: Int = 0
)
