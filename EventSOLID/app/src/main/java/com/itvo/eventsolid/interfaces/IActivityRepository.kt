package com.itvo.eventsolid.interfaces

import com.itvo.eventsolid.models.Activity

interface IActivityRepository {
    fun findActivityByName(name: String): Activity?
    fun addActivity(activity: Activity)
    fun getAllActivities(): List<Activity>
}

