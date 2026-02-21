package com.itvo.eventsolid.services

import com.itvo.eventsolid.interfaces.IActivityService
import com.itvo.eventsolid.models.Activity

class ActivityService : IActivityService {

    // Verificar si hay conflicto de horario
    override fun hasConflictWith(activity1: Activity, activity2: Activity): Boolean {
        return activity1.startTime < activity2.endTime && activity1.endTime > activity2.startTime
    }

    // Verificar si hay espacio disponible
    override fun hasSpace(activity: Activity): Boolean {
        return activity.attendees.size < activity.maxCapacity
    }
}