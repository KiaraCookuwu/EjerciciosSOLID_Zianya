package com.itvo.eventsolid

import com.itvo.eventsolid.models.Activity
import com.itvo.eventsolid.models.Attendee
import com.itvo.eventsolid.services.ActivityService
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalTime

class ActivityServiceTest {

    private lateinit var service: ActivityService
    private lateinit var baseActivity: Activity

    @Before
    fun setup() {
        service = ActivityService()
        baseActivity = Activity("Charla Kotlin", "Dra. Ruiz", LocalTime.of(10, 0), LocalTime.of(11, 0), 2)
    }

    @Test
    fun hasConflictWith_shouldReturnTrue_WhenTimesOverlap() {
        // Conflicto: Empieza antes de que acabe la otra (10:30 a 12:00)
        val overlappingActivity = Activity("Taller", "Speaker", LocalTime.of(10, 30), LocalTime.of(12, 0), 2)

        assertTrue(service.hasConflictWith(baseActivity, overlappingActivity))
    }

    @Test
    fun hasConflictWith_shouldReturnFalse_WhenTimesDoNotOverlap() {
        // Sin conflicto: Empieza después de que acabe la otra (11:00 a 12:00)
        val nonOverlappingActivity = Activity("Taller", "Speaker", LocalTime.of(11, 0), LocalTime.of(12, 0), 2)

        assertFalse(service.hasConflictWith(baseActivity, nonOverlappingActivity))
    }

    @Test
    fun hasSpace_shouldReturnTrue_WhenBelowCapacity() {
        // La capacidad es 2, no tiene inscritos, debe haber espacio
        assertTrue(service.hasSpace(baseActivity))
    }

    @Test
    fun hasSpace_shouldReturnFalse_WhenAtMaxCapacity() {
        // Llenamos la capacidad de la actividad
        baseActivity.attendees.add(Attendee("Uno", "uno@test.com"))
        baseActivity.attendees.add(Attendee("Dos", "dos@test.com"))

        assertFalse(service.hasSpace(baseActivity))
    }
}