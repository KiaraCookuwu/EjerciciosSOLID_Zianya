package com.itvo.bibliotecasolid.modelos

import com.itvo.bibliotecasolid.modelos.Prestamo

data class Usuario(
    val nombre: String,
    val id: Int,
    val librosPrestados: MutableList<Prestamo> = mutableListOf()
)