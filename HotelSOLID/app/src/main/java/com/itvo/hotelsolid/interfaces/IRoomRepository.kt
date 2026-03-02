package com.itvo.hotelsolid.interfaces

import com.itvo.hotelsolid.models.Room

interface IRoomRepository {
    fun findRoomByNumber(number: Int): Room?
    fun addRoom(room: Room)
    fun getAllRooms(): List<Room>
}