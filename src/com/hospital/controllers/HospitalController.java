package com.hospital.controllers;

import com.hospital.utils.ReadingInputs;

import java.util.Scanner;

public class HospitalController {
    private final PatientController patientController;
    private final DoctorController doctorController;
    private final AppointmentController appointmentController;
    public HospitalController(PatientController patientController, DoctorController doctorController, AppointmentController appointmentController){
        this.patientController = patientController;
        this.doctorController = doctorController;
        this.appointmentController = appointmentController;
    }

    public void startHospitalSession(Scanner scanner){
        boolean flag = true;
        int choice;
        while(flag){
            printMainMenu();
            choice = ReadingInputs.readInt(scanner, "Enter your choice: ");
            System.out.println();
            switch(choice){
                case 1-> {
                    System.out.println();
                    patientController.startPatientSession(scanner);
                }
                case 2-> {
                    System.out.println();
                    doctorController.startDoctorSession(scanner);
                }
                case 3-> {
                    System.out.println();
                    appointmentController.startAppointmentSession(scanner);
                }
                case 4-> flag = false;
                default -> System.out.println("Wrong choice");
            }
        }
    }


    public void printMainMenu(){
        System.out.println("""
                Main menu: 
                \t 1. Patient management.
                \t 2. Staff management.
                \t 3. Appointment management
                \t 4. Exit
                """);
    }


}
