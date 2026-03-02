package com.itvo.hotelsolid

import com.itvo.hotelsolid.models.Room
import com.itvo.hotelsolid.models.RoomType
import com.itvo.hotelsolid.repository.RoomRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class RoomRepositoryTest {
    private lateinit var repo: RoomRepository

    @Before
    fun setup() {
        repo = RoomRepository()
    }

    @Test
    fun addRoom_shouldStoreRoomCorrectly() {
        val room = Room(202, 200.0, RoomType.SUITE)
        repo.addRoom(room)

        val found = repo.findRoomByNumber(202)
        assertNotNull(found)
        assertEquals(RoomType.SUITE, found?.roomType)
    }
}