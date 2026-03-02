package com.itvo.eventsolid.services

import com.itvo.eventsolid.interfaces.IActivityRepository
import com.itvo.eventsolid.interfaces.IActivityService
import com.itvo.eventsolid.interfaces.IAttendeeRepository
import com.itvo.eventsolid.interfaces.IEventRepository
import com.itvo.eventsolid.models.Activity
import com.itvo.eventsolid.models.Attendee

class EventSystem(
    private val eventRepo: IEventRepository,
    private val attendeeRepo: IAttendeeRepository,
    private val activityRepo: IActivityRepository,
    private val activityService: IActivityService
) {

    // Validar registro interno
    private fun validateRegistration(attendee: Attendee?, activity: Activity?): Boolean {
        // Validación del Evento
        if (eventRepo.getEvent() == null) {
            println("Error: No hay un evento activo configurado.")
            return false
        }
        if (attendee == null) {
            println("Error: Asistente no encontrado.")
            return false
        }
        if (activity == null) {
            println("Error: Actividad no encontrada.")
            return false
        }
        if (!activityService.hasSpace(activity)) {
            println("Error: Cupo máximo alcanzado para '${activity.name}'.")
            return false
        }

        // Verificar conflictos usando el servicio de actividades
        val hasConflict = attendee.registeredActivities.any {
            activityService.hasConflictWith(it, activity)
        }

        if (hasConflict) {
            println("Error: Conflicto de horario con otra actividad de ${attendee.name}.")
            return false
        }

        return true
    }

    // Registrar a una actividad
    fun registerToActivity(email: String, activityName: String): Boolean {
        val attendee = attendeeRepo.findAttendeeByEmail(email)
        val activity = activityRepo.findActivityByName(activityName)

        if (!validateRegistration(attendee, activity)) {
            return false
        }

        activity!!.attendees.add(attendee!!)
        attendee.registeredActivities.add(activity)

        println("Éxito: ${attendee.name} inscrito en '${activity.name}'.")
        return true
    }
}