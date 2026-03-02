package com.itvo.hotelsolid.repository

import com.itvo.hotelsolid.interfaces.IReservationRepository
import com.itvo.hotelsolid.models.Reservation

class ReservationRepository : IReservationRepository {
    private val reservations: MutableList<Reservation> = mutableListOf()

    override fun addReservation(reservation: Reservation) {
        reservations.add(reservation)
        reservation.guest.reservationHistory.add(reservation)
    }

    override fun removeReservation(reservation: Reservation) {
        reservations.remove(reservation)
        reservation.guest.reservationHistory.remove(reservation)
    }

    override fun getAllReservations(): List<Reservation> {
        return reservations
    }
}