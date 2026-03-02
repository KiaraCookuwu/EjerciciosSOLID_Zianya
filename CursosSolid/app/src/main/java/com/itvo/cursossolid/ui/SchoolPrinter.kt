package com.itvo.cursossolid.ui

import com.itvo.cursossolid.interfaces.IConsultaEscolar

class SchoolPrinter(private val consulta: IConsultaEscolar) {

    fun displayCoursesByStudent(studentId: Int) {
        val student = consulta.findStudent(studentId)
        if (student == null) {
            println("Estudiante no encontrado.")
            return
        }
        println("Cursos de ${student.name}:")
        student.courses.forEach { println("- ${it.description} (${it.code})") }
    }

    fun displayStudentsByCourse(courseCode: String) {
        val course = consulta.findCourse(courseCode)
        if (course == null) {
            println("Curso no encontrado.")
            return
        }
        println("Inscritos en ${course.description}:")
        course.students.forEach { println("• ${it.name}") }
    }
}