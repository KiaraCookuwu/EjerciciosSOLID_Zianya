package com.itvo.hotelsolid.repository

import com.itvo.hotelsolid.interfaces.IGuestRepository
import com.itvo.hotelsolid.models.Guest

class GuestRepository : IGuestRepository {
    private val guests: MutableList<Guest> = mutableListOf()

    override fun findGuestByDni(dni: String): Guest? {
        return guests.find { it.dni == dni }
    }

    override fun addGuest(guest: Guest) {
        guests.add(guest)
    }

    override fun getAllGuests(): List<Guest> {
        return guests
    }
}