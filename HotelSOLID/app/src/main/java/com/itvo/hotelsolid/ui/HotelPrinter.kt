package com.itvo.hotelsolid.ui

import android.os.Build
import androidx.annotation.RequiresApi
import com.itvo.hotelsolid.interfaces.IAvailabilityService
import com.itvo.hotelsolid.interfaces.IReservationRepository
import java.time.LocalDate

class HotelPrinter(
    private val reservationRepo: IReservationRepository,
    private val availabilityService: IAvailabilityService
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun displayAvailability(initialDate: LocalDate, finalDate: LocalDate) {
        println("\n--- Disponibilidad del $initialDate al $finalDate ---")
        // Delegamos al servicio de disponibilidad
        val available = availabilityService.getAvailableRooms(initialDate, finalDate)

        if (available.isEmpty()) {
            println("No hay habitaciones disponibles.")
        } else {
            available.forEach {
                println("• Habitación ${it.number} (${it.roomType}) - $${it.price}/noche")
            }
        }
    }

    fun displayReservations() {
        println("\n--- Reservaciones Actuales ---")
        val list = reservationRepo.getAllReservations()
        if (list.isEmpty()) {
            println("Sin reservaciones.")
        } else {
            list.forEach {
                println("• ${it.guest.name} -> Habitación ${it.room.number}")
            }
        }
    }
}