package com.itvo.hotelsolid

import android.os.Build
import com.itvo.hotelsolid.models.*
import com.itvo.hotelsolid.repository.*
import com.itvo.hotelsolid.services.AvailabilityService
import com.itvo.hotelsolid.services.ReservationSystem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

@androidx.test.filters.SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class ReservationSystemTest {

    private lateinit var guestRepo: GuestRepository
    private lateinit var roomRepo: RoomRepository
    private lateinit var resRepo: ReservationRepository
    private lateinit var availService: AvailabilityService
    private lateinit var system: ReservationSystem

    private lateinit var room101: Room
    private lateinit var juan: Guest

    @Before
    fun setUp() {
        guestRepo = GuestRepository()
        roomRepo = RoomRepository()
        resRepo = ReservationRepository()

        // Creamos el servicio de disponibilidad
        availService = AvailabilityService(roomRepo, resRepo)

        // Lo inyectamos al sistema
        system = ReservationSystem(guestRepo, roomRepo, resRepo, availService)

        // Datos (sin 'available=true')
        room101 = Room(101, 100.0, RoomType.SINGLE)
        roomRepo.addRoom(room101)

        juan = Guest("Juan", "123A", mutableListOf())
        guestRepo.addGuest(juan)
    }

    @Test
    fun makeReservation_shouldSucceed_WhenRoomIsFree() {
        val result = system.makeReservation("123A", 101, LocalDate.now(), LocalDate.now().plusDays(2))

        assertTrue(result)
        assertEquals(1, resRepo.getAllReservations().size)
    }
}