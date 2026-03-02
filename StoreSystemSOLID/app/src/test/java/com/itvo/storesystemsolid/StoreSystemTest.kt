package com.itvo.storesystemsolid

import android.os.Build
import com.itvo.storesystemsolid.models.*
import com.itvo.storesystemsolid.repository.StoreRepository
import com.itvo.storesystemsolid.services.StoreSystem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@androidx.test.filters.SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class StoreSystemTest {

    private lateinit var storeSystem: StoreSystem
    private lateinit var product1: Product
    private lateinit var product2: Product
    private lateinit var customer1: Customer
    private lateinit var cart: Cart
    private lateinit var repo: StoreRepository

    @Before
    fun setup() {
        // 1. Preparamos los datos
        product1 = Product("Laptop", 1200.0, 10)
        product2 = Product("Mouse", 25.0, 50)
        customer1 = Customer("Ana López", "ana.lopez@example.com")

        val productsList = mutableListOf(product1, product2)
        val customersList = mutableListOf(customer1)

        // 2. Inicializamos el Repositorio
        repo = StoreRepository(productsList, customersList)

        // 3. Inyectamos el Repositorio en el Sistema (DIP)
        storeSystem = StoreSystem(repo)

        cart = Cart()
    }

    @Test
    //Deberia retornar true y actualizar stock con exito
    fun makePurchase_Deberia_RetornarTrue_Y_ActualizarStock_Exito() {
        cart.addProduct(product1, 1)
        cart.addProduct(product2, 2)
        val initialStock1 = product1.stock
        val initialStock2 = product2.stock

        val result = storeSystem.makePurchase(customer1.email, cart)

        assertTrue(result)
        assertEquals(initialStock1 - 1, product1.stock)
        assertEquals(initialStock2 - 2, product2.stock)
        assertTrue(customer1.purchaseHistory.isNotEmpty())
    }

    @Test
    fun makePurchase_Deberia_RetornarFalse_SiClienteNoExiste() {
        cart.addProduct(product1, 1)

        val result = storeSystem.makePurchase("inexistente@example.com", cart)

        assertFalse(result)
        assertEquals(10, product1.stock) // El stock no debe cambiar
    }

    @Test
    fun makePurchase_Deberia_RetornarFalse_SiStockInsuficiente() {
        cart.addProduct(product1, 11) // Solo hay 10

        val result = storeSystem.makePurchase(customer1.email, cart)

        assertFalse(result)
        assertEquals(10, product1.stock)
    }

    @Test
    fun carrito_Deberia_Vaciarse_DespuesDeCompraExitosa() {
        cart.addProduct(product1, 1)

        storeSystem.makePurchase(customer1.email, cart)

        assertTrue(cart.products.isEmpty())
    }
}