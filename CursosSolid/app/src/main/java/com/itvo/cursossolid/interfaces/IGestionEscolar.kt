package com.itvo.cursossolid.interfaces

import com.itvo.cursossolid.models.*

interface IGestionEscolar {
    fun inscription(student: Student, course: Course)
    fun addCourse(course: Course)
    fun addStudent(student: Student)
}