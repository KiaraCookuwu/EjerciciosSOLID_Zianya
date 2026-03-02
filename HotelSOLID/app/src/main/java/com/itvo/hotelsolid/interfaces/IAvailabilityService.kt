package com.itvo.hotelsolid.interfaces

import java.time.LocalDate
import com.itvo.hotelsolid.models.Room

interface IAvailabilityService {
    fun getAvailableRooms(initialDate: LocalDate, finalDate: LocalDate): List<Room>
    fun isRoomAvailable(roomNumber: Int, initialDate: LocalDate, finalDate: LocalDate): Boolean
}