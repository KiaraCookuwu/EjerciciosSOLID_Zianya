package com.itvo.cursossolid.interfaces

import com.itvo.cursossolid.models.*

interface IConsultaEscolar {
    fun findStudent(id: Int): Student?
    fun findCourse(code: String): Course?
    fun getAllCourses(): List<Course>
}