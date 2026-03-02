package com.itvo.hotelsolid

import android.os.Build
import com.itvo.hotelsolid.models.*
import com.itvo.hotelsolid.repository.ReservationRepository
import com.itvo.hotelsolid.repository.RoomRepository
import com.itvo.hotelsolid.services.AvailabilityService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

@androidx.test.filters.SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class AvailabilityServiceTest {

    private lateinit var roomRepo: RoomRepository
    private lateinit var resRepo: ReservationRepository
    private lateinit var availabilityService: AvailabilityService

    private lateinit var room101: Room
    private lateinit var juan: Guest

    @Before
    fun setUp() {
        roomRepo = RoomRepository()
        resRepo = ReservationRepository()
        availabilityService = AvailabilityService(roomRepo, resRepo)

        // Nota: Ya no pasamos 'available=true' al constructor
        room101 = Room(101, 100.0, RoomType.SINGLE)
        roomRepo.addRoom(room101)

        juan = Guest("Juan", "123A", mutableListOf())
    }

    @Test
    fun getAvailableRooms_shouldReturnRoom_WhenNoReservationsOverlap() {
        val initialDate = LocalDate.of(2025, 10, 10)
        val finalDate = LocalDate.of(2025, 10, 15)

        // Ocupamos fechas DIFERENTES (del 20 al 25)
        resRepo.addReservation(Reservation(juan, room101, LocalDate.of(2025, 10, 20), LocalDate.of(2025, 10, 25), 300.0))

        val available = availabilityService.getAvailableRooms(initialDate, finalDate)

        assertEquals(1, available.size)
        assertEquals(101, available[0].number)
    }

    @Test
    fun isRoomAvailable_shouldReturnFalse_WhenDatesOverlap() {
        // Reservamos del 1 al 5
        resRepo.addReservation(Reservation(juan, room101, LocalDate.of(2025, 11, 1), LocalDate.of(2025, 11, 5), 200.0))

        // Preguntamos disponibilidad del 2 al 4 (se cruza)
        val isAvailable = availabilityService.isRoomAvailable(101, LocalDate.of(2025, 11, 2), LocalDate.of(2025, 11, 4))

        assertFalse(isAvailable)
    }
}