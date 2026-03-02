package com.itvo.storesystemsolid.ui

import com.itvo.storesystemsolid.interfaces.IStoreRepository

class StorePrinter(private val repository: IStoreRepository) {

    fun showStock() {
        println("\n--- STOCK DISPONIBLE ---")
        repository.getAllProducts().forEach {
            println("• ${it.name}: ${it.stock} unidades - $${it.price}")
        }
    }

    fun showCustomerHistory(email: String) {
        val customer = repository.findCustomerByEmail(email)
        if (customer == null) {
            println("Cliente no encontrado.")
            return
        }

        println("\nHistorial de ${customer.name}:")
        if (customer.purchaseHistory.isEmpty()) {
            println("Sin compras registradas.")
        } else {
            customer.purchaseHistory.forEach { order ->
                println("Fecha: ${order.date} | Total: $${"%.2f".format(order.total)}")
                order.products.zip(order.quantities).forEach { (prod, qty) ->
                    println("   - ${prod.name} x$qty")
                }
            }
        }
    }
}