package com.itvo.bibliotecasolid.modelos

import java.time.LocalDate

data class Prestamo(
    val libro: Libro,
    val usuario: Usuario,
    val fechaPrestamo: LocalDate = LocalDate.now(),
    var fechaDevolucion: LocalDate? = null
)