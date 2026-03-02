package com.itvo.cursossolid.repository

import com.itvo.cursossolid.interfaces.*
import com.itvo.cursossolid.models.*

class SchoolRepository : IConsultaEscolar, IGestionEscolar {

    // Las hacemos privadas para proteger los datos (Encapsulamiento)
    private val courses = mutableListOf<Course>()
    private val teachers = mutableListOf<Teacher>()
    private val students = mutableListOf<Student>()

    // Implementación de IConsultaEscolar
    override fun findStudent(id: Int) = students.find { it.id == id }
    override fun findCourse(code: String) = courses.find { it.code == code }
    override fun getAllCourses() = courses

    // Implementación de IGestionEscolar
    override fun inscription(student: Student, course: Course) {
        course.students.add(student)
        student.courses.add(course)
    }

    // Solución al error de "courses.add"
    override fun addCourse(course: Course) {
        courses.add(course)
    }

    override fun addStudent(student: Student) {
        students.add(student)
    }
}