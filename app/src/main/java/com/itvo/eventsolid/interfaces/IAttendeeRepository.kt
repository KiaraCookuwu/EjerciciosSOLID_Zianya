package com.itvo.eventsolid.interfaces

import com.itvo.eventsolid.models.Attendee

interface IAttendeeRepository {
    fun findAttendeeByEmail(email: String): Attendee?
    fun addAttendee(attendee: Attendee)
    fun getAllAttendees(): List<Attendee>
}