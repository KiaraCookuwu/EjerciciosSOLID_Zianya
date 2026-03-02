package com.itvo.hotelsolid

import com.itvo.hotelsolid.models.*
import com.itvo.hotelsolid.repository.ReservationRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class ReservationRepositoryTest {
    private lateinit var repo: ReservationRepository
    private lateinit var guest: Guest
    private lateinit var room: Room
    private lateinit var res: Reservation

    @Before
    fun setup() {
        repo = ReservationRepository()
        guest = Guest("Test", "111", mutableListOf())
        room = Room(101, 100.0, RoomType.SINGLE)
        res = Reservation(guest, room, LocalDate.now(), LocalDate.now().plusDays(1), 100.0)
    }

    @Test
    fun addReservation_shouldAddToRepo_And_UpdateGuestHistory() {
        repo.addReservation(res)

        assertEquals(1, repo.getAllReservations().size)
        // Verificamos efecto secundario importante: historial del cliente
        assertEquals(1, guest.reservationHistory.size)
    }

    @Test
    fun removeReservation_shouldClearBoth() {
        repo.addReservation(res)
        repo.removeReservation(res)

        assertTrue(repo.getAllReservations().isEmpty())
        assertTrue(guest.reservationHistory.isEmpty())
    }
}