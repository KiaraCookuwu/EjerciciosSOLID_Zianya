package com.itvo.storesystemsolid.interfaces

import com.itvo.storesystemsolid.models.Customer
import com.itvo.storesystemsolid.models.Product

interface IStoreRepository {
    fun findCustomerByEmail(email: String): Customer?
    fun findProductByName(name: String): Product?
    fun getAllProducts(): List<Product>
    fun updateProductStock(product: Product, quantitySold: Int)
}