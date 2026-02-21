package com.itvo.eventsolid.repository

import com.itvo.eventsolid.interfaces.IActivityRepository
import com.itvo.eventsolid.models.Activity

class ActivityRepository : IActivityRepository {
    private val activities = mutableListOf<Activity>()

    override fun findActivityByName(name: String): Activity? {
        return activities.find { it.name == name }
    }

    override fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    override fun getAllActivities(): List<Activity> = activities
}