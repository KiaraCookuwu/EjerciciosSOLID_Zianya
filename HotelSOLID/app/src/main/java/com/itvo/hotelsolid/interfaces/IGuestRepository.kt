package com.itvo.hotelsolid.interfaces

import com.itvo.hotelsolid.models.Guest

interface IGuestRepository {
    fun findGuestByDni(dni: String): Guest?
    fun addGuest(guest: Guest)
    fun getAllGuests(): List<Guest>
}