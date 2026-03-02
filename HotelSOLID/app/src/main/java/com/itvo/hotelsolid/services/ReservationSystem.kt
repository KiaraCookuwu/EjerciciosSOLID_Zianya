package com.itvo.hotelsolid.services

import android.os.Build
import androidx.annotation.RequiresApi
import com.itvo.hotelsolid.interfaces.IAvailabilityService
import com.itvo.hotelsolid.interfaces.IGuestRepository
import com.itvo.hotelsolid.interfaces.IReservationRepository
import com.itvo.hotelsolid.interfaces.IRoomRepository
import com.itvo.hotelsolid.models.Reservation
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class ReservationSystem(
    private val guestRepo: IGuestRepository,
    private val roomRepo: IRoomRepository,
    private val reservationRepo: IReservationRepository,
    private val availabilityService: IAvailabilityService // Inyectamos el nuevo servicio
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun makeReservation(dni: String, roomNumber: Int, arrivalDate: LocalDate,
                        departureDate: LocalDate): Boolean {
        val guest = guestRepo.findGuestByDni(dni)
        val room = roomRepo.findRoomByNumber(roomNumber)

        if (guest == null) {
            println("Error: Huésped no encontrado.")
            return false
        }
        if (room == null) {
            println("Error: Habitación no encontrada.")
            return false
        }

        if (arrivalDate >= departureDate) {
            println("Error: Fechas inválidas.")
            return false
        }

        // USAMOS EL NUEVO SERVICIO PARA VALIDAR
        if (!availabilityService.isRoomAvailable(roomNumber, arrivalDate,
                departureDate)) {
            println("Error: Habitación no disponible en esas fechas.")
            return false
        }

        val nights = ChronoUnit.DAYS.between(arrivalDate,
            departureDate)
        val totalAmount = nights * room.price

        val reservation = Reservation(
            guest = guest,
            room = room,
            arrivalDate = arrivalDate,
            departureDate = departureDate,
            total = totalAmount
        )

        reservationRepo.addReservation(reservation)
        println("Reservación exitosa para ${guest.name}. Total: $$totalAmount")
        return true
    }

    fun cancelReservation(dni: String, roomNumber: Int, arrivalDate: LocalDate): Boolean {
        val reservation = reservationRepo.getAllReservations().find {
            it.guest.dni == dni &&
                    it.room.number == roomNumber &&
                    it.arrivalDate == arrivalDate
        }

        if (reservation == null) {
            println("Error: Reservación no encontrada.")
            return false
        }

        reservationRepo.removeReservation(reservation)
        println("Reservación cancelada.")
        return true
    }
}