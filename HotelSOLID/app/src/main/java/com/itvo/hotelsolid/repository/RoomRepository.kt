package com.itvo.hotelsolid.repository

import com.itvo.hotelsolid.interfaces.IRoomRepository
import com.itvo.hotelsolid.models.Room

class RoomRepository : IRoomRepository {
    private val rooms: MutableList<Room> = mutableListOf()

    override fun findRoomByNumber(number: Int): Room? {
        return rooms.find { it.number == number }
    }

    override fun addRoom(room: Room) {
        rooms.add(room)
    }

    override fun getAllRooms(): List<Room> {
        return rooms
    }
}