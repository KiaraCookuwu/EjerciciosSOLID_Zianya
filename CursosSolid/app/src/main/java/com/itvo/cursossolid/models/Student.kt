package com.itvo.cursossolid.models

data class Student(
    val id: Int,
    val name: String,
    val courses: MutableList<Course> = mutableListOf()
)
