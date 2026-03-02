package com.itvo.bibliotecasolid.repositorio

import com.itvo.bibliotecasolid.interfaces.IConsultaBiblioteca
import com.itvo.bibliotecasolid.interfaces.IGestionBiblioteca
import com.itvo.bibliotecasolid.modelos.Libro
import com.itvo.bibliotecasolid.modelos.Prestamo
import com.itvo.bibliotecasolid.modelos.Usuario

class BibliotecaRepository : IConsultaBiblioteca, IGestionBiblioteca {
    private val libros = mutableListOf<Libro>()
    private val usuarios = mutableListOf<Usuario>()
    private val prestamos = mutableListOf<Prestamo>()

    override fun agregarLibro(libro: Libro) { libros.add(libro) }

    override fun registrarUsuario(usuario: Usuario) { usuarios.add(usuario) }

    override fun guardarPrestamo(prestamo: Prestamo) { prestamos.add(prestamo) }

    override fun obtenerLibrosDisponibles(): List<Libro> = libros.filter { it.disponible }

    override fun obtenerPrestamosActivos(): List<Prestamo> = prestamos.filter { it.fechaDevolucion == null }
}