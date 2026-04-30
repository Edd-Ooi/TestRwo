package com.habittracker.nativeapp

data class Habit(
    val id: String,
    var name: String,
    var anchor: String,
    var micro: String,
    var feel: String,
    var done: Boolean = false,
    var streak: Int = 0
)
