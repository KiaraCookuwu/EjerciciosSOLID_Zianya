package com.itvo.storesystemsolid.repository

import com.itvo.storesystemsolid.interfaces.IStoreRepository
import com.itvo.storesystemsolid.models.Customer
import com.itvo.storesystemsolid.models.Product

class StoreRepository(
    private val products: MutableList<Product>,
    private val customers: MutableList<Customer>
) : IStoreRepository {

    override fun findCustomerByEmail(email: String): Customer? {
        return customers.find { it.email == email }
    }

    override fun findProductByName(name: String): Product? {
        return products.find { it.name == name }
    }

    override fun getAllProducts(): List<Product> {
        return products
    }

    override fun updateProductStock(product: Product, quantitySold: Int) {
        product.stock -= quantitySold
    }
}