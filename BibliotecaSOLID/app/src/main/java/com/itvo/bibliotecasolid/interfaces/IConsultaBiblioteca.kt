package com.itvo.bibliotecasolid.interfaces

import com.itvo.bibliotecasolid.modelos.Libro
import com.itvo.bibliotecasolid.modelos.Prestamo

interface IConsultaBiblioteca {
    fun obtenerLibrosDisponibles(): List<Libro>
    fun obtenerPrestamosActivos(): List<Prestamo>
}