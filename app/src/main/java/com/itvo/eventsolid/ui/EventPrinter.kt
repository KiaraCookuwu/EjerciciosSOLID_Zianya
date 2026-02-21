package com.itvo.eventsolid.ui

import com.itvo.eventsolid.interfaces.IActivityRepository
import com.itvo.eventsolid.interfaces.IAttendeeRepository

class EventPrinter(
    private val attendeeRepo: IAttendeeRepository,
    private val activityRepo: IActivityRepository
) {

    fun showScheduleByAttendee(email: String) {
        val attendee = attendeeRepo.findAttendeeByEmail(email)
        if (attendee == null) {
            println("Asistente no encontrado.")
            return
        }

        println("\n--- Cronograma de ${attendee.name} ---")
        if (attendee.registeredActivities.isEmpty()) {
            println("No tiene actividades inscritas.")
        } else {
            // Ordenamos por hora de inicio para mostrar un horario lógico
            attendee.registeredActivities.sortedBy { it.startTime }.forEach {
                println("• ${it.startTime} a ${it.endTime} | ${it.name} (Ponente: ${it.speaker})")
            }
        }
    }

    fun showAttendeesByActivity(activityName: String) {
        val activity = activityRepo.findActivityByName(activityName)
        if (activity == null) {
            println("Actividad no encontrada.")
            return
        }

        println("\n--- Asistentes en '${activity.name}' ---")
        if (activity.attendees.isEmpty()) {
            println("Sin inscritos aún.")
        } else {
            activity.attendees.forEach {
                println("• ${it.name} (${it.email})")
            }
        }
    }
}