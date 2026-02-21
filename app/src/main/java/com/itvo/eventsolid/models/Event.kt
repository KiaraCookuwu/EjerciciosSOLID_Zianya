package com.itvo.eventsolid.models

import java.time.LocalDate

data class Event(
    val name: String,
    val date: LocalDate,
    val activities: MutableList<Activity> = mutableListOf()
)
