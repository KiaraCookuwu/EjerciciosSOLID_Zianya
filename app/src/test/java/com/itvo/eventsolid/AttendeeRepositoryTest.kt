package com.itvo.eventsolid

import com.itvo.eventsolid.models.Attendee
import com.itvo.eventsolid.repository.AttendeeRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AttendeeRepositoryTest {

    private lateinit var repo: AttendeeRepository
    private lateinit var attendee: Attendee

    @Before
    fun setup() {
        repo = AttendeeRepository()
        attendee = Attendee("Ana López", "ana@example.com")
    }

    @Test
    fun findAttendeeByEmail_shouldReturnNull_WhenNotExists() {
        // Prueba: findAttendeeByEmail cuando no existe
        assertNull(repo.findAttendeeByEmail("noexiste@test.com"))
    }

    @Test
    fun addAttendee_and_findAttendeeByEmail_shouldWorkCorrectly() {
        // Prueba: addAttendee guarda y findAttendeeByEmail lo encuentra
        repo.addAttendee(attendee)

        val found = repo.findAttendeeByEmail("ana@example.com")
        assertNotNull(found)
        assertEquals("Ana López", found?.name)
    }

    @Test
    fun getAllAttendees_shouldReturnAllStoredAttendees() {
        // Prueba: getAllAttendees retorna la lista completa
        assertTrue(repo.getAllAttendees().isEmpty())

        repo.addAttendee(attendee)
        repo.addAttendee(Attendee("Juan", "juan@test.com"))

        val allAttendees = repo.getAllAttendees()
        assertEquals(2, allAttendees.size)
    }
}