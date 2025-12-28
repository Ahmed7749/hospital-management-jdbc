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
                case 1 -> hospitalManagement.bookAppointment(ReadingInputs.readAppointment(scanner));
                case 2 -> startUpdateSubSession();
                case 3 -> AppointmentView.printAppointments(hospitalManagement.getAppointmentList());
                case 4 -> AppointmentView.printAppointments(hospitalManagement.getAppointmentsForPatient(ReadingInputs.readInt(scanner, "Enter patient's id")));
                case 5 -> hospitalManagement.deleteAppointmentById(ReadingInputs.readInt(scanner,"Enter appointment id"));
                case 6 -> {
                    flag = false;
                    System.out.println("Appointment session closed");
                }
            }
        }
    }


    public void startUpdateSubSession(){
        boolean flag = true;
        int choice;
        while(flag){
            AppointmentView.printUpdateSubmenu();
            choice = ReadingInputs.readInt(scanner, "Enter your choice: ");
            switch(choice){
                case 1 -> {
                    hospitalManagement.updateAppointmentTime(ReadingInputs.readInt(scanner,"Enter appointment id"), ReadingInputs.readLocalTime(scanner));
                    flag = false;
                }
                case 2 -> {
                    hospitalManagement.updateAppointmentDate(ReadingInputs.readInt(scanner, "Enter appointment id"), ReadingInputs.readLocalDate(scanner));
                    flag = false;
                }
                default -> System.out.println("Wrong choice (: ");
            }
        }
    }
}
