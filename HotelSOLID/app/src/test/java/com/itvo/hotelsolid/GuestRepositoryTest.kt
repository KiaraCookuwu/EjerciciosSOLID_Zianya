package com.itvo.hotelsolid

import com.itvo.hotelsolid.models.Guest
import com.itvo.hotelsolid.repository.GuestRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class GuestRepositoryTest {
    private lateinit var repo: GuestRepository

    @Before
    fun setup() {
        repo = GuestRepository()
    }

    @Test
    fun addGuest_shouldStoreGuestCorrectly() {
        val guest = Guest("Ana", "555", mutableListOf())
        repo.addGuest(guest)

        val found = repo.findGuestByDni("555")
        assertNotNull(found)
        assertEquals("Ana", found?.name)
    }
}