package com.itvo.bibliotecasolid.ui

import com.itvo.bibliotecasolid.interfaces.IConsultaBiblioteca

class BibliotecaPrinter(private val consulta: IConsultaBiblioteca) {
    fun mostrarDisponibles() {
        println("\n--- LIBROS DISPONIBLES ---")
        consulta.obtenerLibrosDisponibles().forEach { println("- ${it.titulo} (${it.autor})") }
    }

    fun mostrarPrestados() {
        println("\n--- LIBROS PRESTADOS ---")
        consulta.obtenerPrestamosActivos().forEach {
            println("- ${it.libro.titulo} prestado a ${it.usuario.nombre}")
        }
    }
}