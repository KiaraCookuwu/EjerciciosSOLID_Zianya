package com.itvo.eventsolid.interfaces

import com.itvo.eventsolid.models.Activity

interface IActivityService {
    fun hasConflictWith(activity1: Activity, activity2: Activity): Boolean
    fun hasSpace(activity: Activity): Boolean
}