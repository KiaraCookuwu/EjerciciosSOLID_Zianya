package com.itvo.eventsolid.repository

import com.itvo.eventsolid.interfaces.IEventRepository
import com.itvo.eventsolid.models.Event

class EventRepository : IEventRepository {
    private var currentEvent: Event? = null

    override fun getEvent(): Event? = currentEvent

    override fun setEvent(event: Event) {
        currentEvent = event
    }
}