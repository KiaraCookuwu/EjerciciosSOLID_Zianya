package com.itvo.eventsolid

import com.itvo.eventsolid.models.Activity
import com.itvo.eventsolid.repository.ActivityRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalTime

class ActivityRepositoryTest {

    private lateinit var repo: ActivityRepository
    private lateinit var activity: Activity

    @Before
    fun setup() {
        repo = ActivityRepository()
        activity = Activity("Charla Kotlin", "Dra. Ruiz", LocalTime.of(10, 0), LocalTime.of(11, 0), 2)
    }

    @Test
    fun findActivityByName_shouldReturnNull_WhenActivityDoesNotExist() {
        // Prueba: findActivityByName cuando no existe
        assertNull(repo.findActivityByName("Inexistente"))
    }

    @Test
    fun addActivity_and_findActivityByName_shouldWorkCorrectly() {
        // Prueba: addActivity guarda y findActivityByName lo encuentra
        repo.addActivity(activity)

        val found = repo.findActivityByName("Charla Kotlin")
        assertNotNull(found)
        assertEquals("Dra. Ruiz", found?.speaker)
    }

    @Test
    fun getAllActivities_shouldReturnAllStoredActivities() {
        // Prueba: getAllActivities retorna la lista completa
        assertTrue(repo.getAllActivities().isEmpty()) // Debe empezar vacía

        repo.addActivity(activity)
        repo.addActivity(Activity("Taller UI", "Pedro", LocalTime.of(12,0), LocalTime.of(13,0), 5))

        val allActivities = repo.getAllActivities()
        assertEquals(2, allActivities.size)
    }
}