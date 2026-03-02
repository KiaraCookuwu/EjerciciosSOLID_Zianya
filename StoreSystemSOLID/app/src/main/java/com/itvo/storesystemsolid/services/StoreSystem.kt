package com.itvo.storesystemsolid.services

import android.os.Build
import androidx.annotation.RequiresApi
import com.itvo.storesystemsolid.models.*
import com.itvo.storesystemsolid.interfaces.IStoreRepository
import java.time.LocalDate

class StoreSystem(private val repository: IStoreRepository) {

    private val IVA = 0.16

    // REGLAS DE NEGOCIO (Método privado solicitado)
    private fun validatePurchase(customer: Customer?, cart: Cart): Boolean {
        if (customer == null) {
            println("Error: Cliente no encontrado.")
            return false
        }
        if (cart.products.isEmpty()) {
            println("Error: El carrito está vacío.")
            return false
        }
        // Verificar stock
        for ((product, quantity) in cart.products) {
            if (product.stock < quantity) {
                println("Error: Stock insuficiente para '${product.name}'. Disponible: ${product.stock}")
                return false
            }
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun makePurchase(email: String, cart: Cart): Boolean {
        val customer = repository.findCustomerByEmail(email)

        if (!validatePurchase(customer, cart)) {
            return false
        }

        // 1. Cálculos
        val subtotal = cart.calculateSubtotal()
        val total = subtotal * (1 + IVA)

        // 2. Actualizar Stock
        cart.products.forEach { (product, quantity) ->
            repository.updateProductStock(product, quantity)
        }

        // 3. Generar Orden
        val order = Order(
            customer = customer!!,
            products = cart.products.keys.toList(),
            quantities = cart.products.values.toList(),
            date = LocalDate.now(),
            total = total
        )

        // 4. Guardar historial
        customer.purchaseHistory.add(order)

        // 5. Limpiar carrito
        cart.clear()

        println("Compra realizada con éxito. Total: $$total")
        return true
    }
}