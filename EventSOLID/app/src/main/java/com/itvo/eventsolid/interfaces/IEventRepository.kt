package com.itvo.eventsolid.interfaces

import com.itvo.eventsolid.models.Event

interface IEventRepository {
    fun getEvent(): Event?
    fun setEvent(event: Event)
}