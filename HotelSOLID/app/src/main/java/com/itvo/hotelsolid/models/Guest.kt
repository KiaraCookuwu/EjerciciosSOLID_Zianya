package com.itvo.hotelsolid.models

data class Guest(
    val name: String,
    val dni: String,
    val reservationHistory: MutableList<Reservation> = mutableListOf()
)