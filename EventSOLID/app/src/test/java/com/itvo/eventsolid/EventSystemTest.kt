package com.itvo.eventsolid

import com.itvo.eventsolid.models.*
import com.itvo.eventsolid.repository.*
import com.itvo.eventsolid.services.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime

class EventSystemTest {

    private lateinit var eventRepo: EventRepository
    private lateinit var attendeeRepo: AttendeeRepository
    private lateinit var activityRepo: ActivityRepository
    private lateinit var activityService: ActivityService
    private lateinit var eventSystem: EventSystem

    private lateinit var activity1: Activity
    private lateinit var activity2: Activity
    private lateinit var attendee1: Attendee

    @Before
    fun setup() {
        eventRepo = EventRepository()
        attendeeRepo = AttendeeRepository()
        activityRepo = ActivityRepository()
        activityService = ActivityService()

        eventSystem = EventSystem(eventRepo, attendeeRepo, activityRepo, activityService)

        // Configuración inicial del Evento
        val event = Event("TechFest", LocalDate.of(2025, 9, 15))
        eventRepo.setEvent(event)

        // Configuración de Actividades
        activity1 = Activity("Charla Kotlin", "Dra. Ruiz", LocalTime.of(10, 0), LocalTime.of(11, 0), 2)
        activity2 = Activity("Taller Android", "Ing. Torres", LocalTime.of(10, 30), LocalTime.of(12, 0), 2)
        activityRepo.addActivity(activity1)
        activityRepo.addActivity(activity2)

        // Configuración de Asistentes
        attendee1 = Attendee("Ana López", "ana@example.com")
        attendeeRepo.addAttendee(attendee1)
    }

    // --- TESTS DEL REPOSITORIO DE EVENTOS ---

    @Test
    fun eventRepository_getEvent_shouldReturnNull_WhenNoEventIsSet() {
        // Usamos una instancia nueva para comprobar que por defecto es null
        val freshRepo = EventRepository()
        assertNull(freshRepo.getEvent())
    }

    @Test
    fun eventRepository_setEvent_and_getEvent_shouldStoreAndReturnEvent() {
        val freshRepo = EventRepository()
        val event = Event("Hackathon", LocalDate.of(2025, 10, 20))
        freshRepo.setEvent(event)

        val retrievedEvent = freshRepo.getEvent()
        assertNotNull(retrievedEvent)
        assertEquals("Hackathon", retrievedEvent?.name)
    }

    // --- TESTS DEL SISTEMA DE EVENTOS ---

    @Test
    fun registerToActivity_shouldFail_WhenNoEventIsSet() {
        // Falla si el repositorio de eventos está vacío
        val emptySystem = EventSystem(EventRepository(), attendeeRepo, activityRepo, activityService)
        val result = emptySystem.registerToActivity("ana@example.com", "Charla Kotlin")

        assertFalse(result)
    }

    @Test
    fun registerToActivity_shouldSucceed_WhenValid() {
        // Registro exitoso sin conflicto ni cupo lleno
        val result = eventSystem.registerToActivity("ana@example.com", "Charla Kotlin")

        assertTrue(result)
        assertEquals(1, activity1.attendees.size)
    }

    @Test
    fun registerToActivity_shouldFail_WhenScheduleConflicts() {
        // Falla por conflicto de horario (10:00-11:00 vs 10:30-12:00)
        eventSystem.registerToActivity("ana@example.com", "Charla Kotlin")
        val result = eventSystem.registerToActivity("ana@example.com", "Taller Android")

        assertFalse(result)
    }

    @Test
    fun registerToActivity_shouldFail_WhenCapacityIsFull() {
        // Llenamos el cupo de la actividad (capacidad = 2)
        attendeeRepo.addAttendee(Attendee("Juan", "juan@example.com"))
        attendeeRepo.addAttendee(Attendee("Carlos", "carlos@example.com"))

        eventSystem.registerToActivity("ana@example.com", "Charla Kotlin")
        eventSystem.registerToActivity("juan@example.com", "Charla Kotlin")

        // El tercer asistente no debería poder inscribirse
        val result = eventSystem.registerToActivity("carlos@example.com", "Charla Kotlin")

        assertFalse(result)
    }
}