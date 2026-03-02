package com.itvo.storesystemsolid.models

import java.time.LocalDate

data class Order(
    val customer: Customer,
    val products: List<Product>,
    val quantities: List<Int>,
    val date: LocalDate,
    val total: Double
)