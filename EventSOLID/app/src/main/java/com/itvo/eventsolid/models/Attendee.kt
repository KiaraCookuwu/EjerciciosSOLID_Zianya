package com.itvo.eventsolid.models

data class Attendee(
    val name: String,
    val email: String,
    val registeredActivities: MutableList<Activity> = mutableListOf()
)