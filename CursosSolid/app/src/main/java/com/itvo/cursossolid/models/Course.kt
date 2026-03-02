package com.itvo.cursossolid.models

data class Course(
    val description: String,
    val code: String,
    val teacher: Teacher,
    val students: MutableList<Student> = mutableListOf()
)
