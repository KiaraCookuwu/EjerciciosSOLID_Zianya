package com.itvo.cursossolid.services

import com.itvo.cursossolid.interfaces.*
import com.itvo.cursossolid.models.*

class CourseSystem(
    private val consulta: IConsultaEscolar,
    private val gestion: IGestionEscolar
) {

    // Método de validación interno solicitado
    private fun validarInscripcion(student: Student?, course: Course?): Boolean {
        if (course == null) {
            println("Error: Curso no encontrado.")
            return false
        }
        if (student == null) {
            println("Error: Estudiante no encontrado.")
            return false
        }
        if (course.students.size >= 30) {
            println("Error: Curso '${course.description}' lleno (límite 30).")
            return false
        }
        if (course.students.any { it.id == student.id }) {
            println("Error: Estudiante '${student.name}' ya inscrito.")
            return false
        }
        return true
    }

    fun enrollStudentInCourse(courseCode: String, studentId: Int): Boolean {
        val course = consulta.findCourse(courseCode)
        val student = consulta.findStudent(studentId)

        if (validarInscripcion(student, course)) {
            gestion.inscription(student!!, course!!)
            println("Éxito: '${student.name}' inscrito en '${course.description}'")
            return true
        }
        return false
    }
}