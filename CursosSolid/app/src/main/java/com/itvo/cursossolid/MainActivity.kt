package com.itvo.cursossolid
import com.itvo.cursossolid.models.*
import com.itvo.cursossolid.repository.SchoolRepository
import com.itvo.cursossolid.services.CourseSystem
import com.itvo.cursossolid.ui.SchoolPrinter

fun main() {
    val repo = SchoolRepository()

    val system = CourseSystem(consulta = repo, gestion = repo)
    val printer = SchoolPrinter(consulta = repo)

    val teacher = Teacher ("Alan Turing", "Computación")

    val course = Course("Estructuras de Datos", "ED01", teacher)
    val student = Student(1, "Ambrosio")

    repo.addCourse(course)
    repo.addStudent(student)

    system.enrollStudentInCourse("ED01", 1)

    printer.displayStudentsByCourse("ED01")
}