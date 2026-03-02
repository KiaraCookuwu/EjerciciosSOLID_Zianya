package com.itvo.hotelsolid.services

import android.os.Build
import androidx.annotation.RequiresApi
import com.itvo.hotelsolid.interfaces.IAvailabilityService
import com.itvo.hotelsolid.interfaces.IReservationRepository
import com.itvo.hotelsolid.interfaces.IRoomRepository
import com.itvo.hotelsolid.models.Room
import java.time.LocalDate

class AvailabilityService(
    private val roomRepo: IRoomRepository,
    private val reservationRepo: IReservationRepository
) : IAvailabilityService {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getAvailableRooms(initialDate: LocalDate, finalDate: LocalDate): List<Room> {
        val allReservations = reservationRepo.getAllReservations()
        val allRooms = roomRepo.getAllRooms()

        // Lógica pura: Encontrar habitaciones ocupadas en ese rango
        val occupiedRooms = allReservations.filter { res ->
            // Fórmula de solapamiento de fechas:
            // (LlegadaReserva < SalidaSolicitada) Y (SalidaReserva > LlegadaSolicitada)
            res.arrivalDate < finalDate && res.departureDate > initialDate
        }.map { it.room }

        // Retornamos las que NO están en la lista de ocupadas
        return allRooms.filter { !occupiedRooms.contains(it) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun isRoomAvailable(roomNumber: Int, initialDate: LocalDate, finalDate: LocalDate): Boolean {
        val availableRooms = getAvailableRooms(initialDate, finalDate)
        return availableRooms.any { it.number == roomNumber }
    }
}