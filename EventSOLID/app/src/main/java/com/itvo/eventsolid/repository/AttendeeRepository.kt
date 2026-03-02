package com.itvo.eventsolid.repository

import com.itvo.eventsolid.interfaces.IAttendeeRepository
import com.itvo.eventsolid.models.Attendee

class AttendeeRepository : IAttendeeRepository {
    private val attendees = mutableListOf<Attendee>()

    override fun findAttendeeByEmail(email: String): Attendee? {
        return attendees.find { it.email == email }
    }

    override fun addAttendee(attendee: Attendee) {
        attendees.add(attendee)
    }

    override fun getAllAttendees(): List<Attendee> = attendees
}