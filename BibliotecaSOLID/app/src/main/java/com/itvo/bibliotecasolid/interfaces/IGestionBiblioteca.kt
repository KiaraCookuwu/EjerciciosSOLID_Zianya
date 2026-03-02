package com.itvo.bibliotecasolid.interfaces

import com.itvo.bibliotecasolid.modelos.Libro
import com.itvo.bibliotecasolid.modelos.Prestamo
import com.itvo.bibliotecasolid.modelos.Usuario

interface IGestionBiblioteca {
    fun agregarLibro(libro: Libro)
    fun registrarUsuario(usuario: Usuario)
    fun guardarPrestamo(prestamo: Prestamo)
}