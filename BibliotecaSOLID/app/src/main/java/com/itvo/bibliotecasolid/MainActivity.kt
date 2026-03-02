package com.itvo.bibliotecasolid

import com.itvo.bibliotecasolid.modelos.Libro
import com.itvo.bibliotecasolid.modelos.Usuario
import com.itvo.bibliotecasolid.repositorio.BibliotecaRepository
import com.itvo.bibliotecasolid.servicios.SistemaBiblioteca
import com.itvo.bibliotecasolid.ui.BibliotecaPrinter

fun main() {
    val repo = BibliotecaRepository()
    val sistema = SistemaBiblioteca(repo)
    val printer = BibliotecaPrinter(repo)

    // 3. Preparación de datos
    val libro1 = Libro("El Quijote", "Miguel de Cervantes", "001")
    val libro2 = Libro("Cien años de soledad", "Gabriel García Márquez", "002")
    val libro3 = Libro("La Odisea", "Homero", "003")
    val libro4 = Libro("El Principito", "Saint-Exupéry", "004")

    repo.agregarLibro(libro1)
    repo.agregarLibro(libro2)
    repo.agregarLibro(libro3)
    repo.agregarLibro(libro4)

    val usuario1 = Usuario("Ana", 1)
    repo.registrarUsuario(usuario1)

    // 4. Ejecución
    printer.mostrarDisponibles()

    sistema.prestarLibro(usuario1, libro1)
    sistema.prestarLibro(usuario1, libro2)
    sistema.prestarLibro(usuario1, libro3)
    sistema.prestarLibro(usuario1, libro4) // Fallará por la regla de límite de 3

    printer.mostrarPrestados()

    sistema.devolverLibro(usuario1, libro1)
    printer.mostrarDisponibles()
}