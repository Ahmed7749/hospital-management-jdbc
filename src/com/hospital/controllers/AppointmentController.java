package com.hospital.controllers;

import com.hospital.main.HospitalManagement;
import com.hospital.utils.ReadingInputs;
import com.hospital.view.AppointmentView;

import java.util.Scanner;

public class AppointmentController {
    private final HospitalManagement hospitalManagement;
    public AppointmentController(HospitalManagement hospitalManagement){
        this.hospitalManagement = hospitalManagement;
    }

    public void startAppointmentSession(Scanner scanner){
        boolean flag = true;
        int choice;
        while(flag){
            AppointmentView.printAppointmentMenu();
            choice = ReadingInputs.readInt(scanner, "Enter your choice: ");
            switch(choice){
                case 1 -> {
                    boolean booked = hospitalManagement.bookAppointment(ReadingInputs.readAppointment(scanner));
                    System.out.println((booked) ? "Appointment booked success" : "Failed to book appointment");
                }
                case 2 -> startUpdateSubSession(scanner);
                case 3 -> AppointmentView.printAppointments(hospitalManagement.getAppointmentList());
                case 4 -> AppointmentView.printAppointments(hospitalManagement.getAppointmentsForPatient(ReadingInputs.readInt(scanner, "Enter patient's id")));
                case 5 -> {
                    boolean deleted = hospitalManagement.deleteAppointmentById(ReadingInputs.readInt(scanner,"Enter appointment id"));
                    System.out.println((deleted) ? "Appointment deleted success" : "Appointment delete failed");
                }
                case 6 -> {
                    flag = false;
                    System.out.println("Appointment session closed");
                }
            }
        }
    }


    public void startUpdateSubSession(Scanner scanner){
        boolean flag = true;
        int choice;
        while(flag){
            AppointmentView.printUpdateSubmenu();
            choice = ReadingInputs.readInt(scanner, "Enter your choice: ");
            switch(choice){
                case 1 -> {
                    boolean updated = hospitalManagement.updateAppointmentTime(ReadingInputs.readInt(scanner,"Enter appointment id"), ReadingInputs.readLocalTime(scanner));
                    System.out.println((updated) ? "time update success" : "Failed to update time");
                    flag = false;
                }
                case 2 -> {
                    boolean updated = hospitalManagement.updateAppointmentDate(ReadingInputs.readInt(scanner, "Enter appointment id"), ReadingInputs.readLocalDate(scanner));
                    System.out.println((updated) ? "date update success" : "Failed to update date");
                    flag = false;
                }
                default -> System.out.println("Wrong choice (: ");
            }
        }
    }
}
