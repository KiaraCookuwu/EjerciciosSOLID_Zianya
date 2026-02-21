package com.itvo.eventsolid.models

import com.itvo.eventsolid.models.Attendee
import java.time.LocalTime

data class Activity(
    val name: String,
    val speaker: String,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val maxCapacity: Int,
    val attendees: MutableList<Attendee> = mutableListOf()
)