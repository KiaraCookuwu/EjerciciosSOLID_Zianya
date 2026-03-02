package com.itvo.bibliotecasolid.servicios

import com.itvo.bibliotecasolid.interfaces.IGestionBiblioteca
import com.itvo.bibliotecasolid.modelos.Libro
import com.itvo.bibliotecasolid.modelos.Prestamo
import com.itvo.bibliotecasolid.modelos.Usuario
import java.time.LocalDate

class SistemaBiblioteca(private val datos: IGestionBiblioteca) {

    private fun validarPrestamo(usuario: Usuario, libro: Libro): Boolean {
        // Regla 1: Disponibilidad
        if (!libro.disponible) {
            println("Error: El libro '${libro.titulo}' no está disponible.")
            return false
        }
        // Regla 2: Límite de libros (Máximo 3)
        val activos = usuario.librosPrestados.count { it.fechaDevolucion == null }
        if (activos >= 3) {
            println("Error: ${usuario.nombre} ya tiene el máximo de 3 libros.")
            return false
        }
        return true
    }

    fun prestarLibro(usuario: Usuario, libro: Libro): Boolean {
        if (validarPrestamo(usuario, libro)) {
            val prestamo = Prestamo(libro, usuario)
            datos.guardarPrestamo(prestamo)
            usuario.librosPrestados.add(prestamo)
            libro.disponible = false
            println("Éxito: Préstamo realizado a ${usuario.nombre}")
            return true
        }
        return false
    }

    fun devolverLibro(usuario: Usuario, libro: Libro): Boolean {
        val prestamo = usuario.librosPrestados.find { it.libro == libro && it.fechaDevolucion == null }
        return if (prestamo != null) {
            prestamo.fechaDevolucion = LocalDate.now()
            libro.disponible = true
            println("Éxito: Libro devuelto.")
            true
        } else false
    }
}