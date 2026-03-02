package com.itvo.hotelsolid.interfaces

import com.itvo.hotelsolid.models.Reservation

interface IReservationRepository {
    fun addReservation(reservation: Reservation)
    fun removeReservation(reservation: Reservation)
    fun getAllReservations(): List<Reservation>
}