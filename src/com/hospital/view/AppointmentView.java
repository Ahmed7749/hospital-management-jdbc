package com.hospital.view;

import com.hospital.pojos.Appointment;

import java.util.List;

public class AppointmentView {
    public static void printAppointmentMenu(){
        System.out.println("""
                1. Book appointment
                2. Update appointment
                3. View appointments (for all hospital)
                4. View appointments (for a single patient)
                5. Delete appointment
                6. Back to main menu
                """);
    }

    public static void printUpdateSubmenu(){
        System.out.println("""
                1. update appointment time
                2. update appointment date
                """);
    }

    public static void printAppointments(List<Appointment> appointmentList) {
        if (appointmentList.isEmpty()) {
            System.out.println("No appointments to print P:");
            return;
        }
        appointmentList.forEach(System.out::println);
    }
}
