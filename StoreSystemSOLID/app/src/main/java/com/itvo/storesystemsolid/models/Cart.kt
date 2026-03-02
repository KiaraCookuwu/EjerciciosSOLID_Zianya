package com.itvo.storesystemsolid.models

class Cart {
    val products: MutableMap<Product, Int> = mutableMapOf()

    fun addProduct(product: Product, quantity: Int) {
        if (quantity <= 0) return

        if (product in products) {
            products[product] = products[product]!! + quantity
        } else {
            products[product] = quantity
        }
    }

    fun clear() {
        products.clear()
    }

    fun calculateSubtotal(): Double {
        return products.entries.sumOf { it.key.price * it.value }
    }
}