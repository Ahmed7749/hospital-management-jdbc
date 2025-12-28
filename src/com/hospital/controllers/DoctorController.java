package com.hospital.controllers;


import com.hospital.main.HospitalManagement;
import com.hospital.utils.ReadingInputs;
import com.hospital.view.DoctorView;

import java.util.Scanner;

public class DoctorController {
    private final HospitalManagement hospitalManagement;
    public DoctorController(HospitalManagement hospitalManagement){
        this.hospitalManagement = hospitalManagement;
    }

    public void startDoctorSession(Scanner scanner){
        boolean flag = true;
        int choice;
        while(flag){
            DoctorView.printDoctorMenu();
            choice = ReadingInputs.readInt(scanner, "Please enter your choice: ");
            switch(choice){
                case 1 -> {
                    System.out.println();
                    hospitalManagement.addDoctor(ReadingInputs.readDoctor(scanner));
                }
                case 2 -> {
                    System.out.println();
                    int doctorId = ReadingInputs.readInt(scanner, "Enter doctor id: ");
                    scanner.nextLine();
                    String doctorName = ReadingInputs.readString(scanner,"Enter new name: ");
                    hospitalManagement.updateDoctorName(doctorId,doctorName);
                }
                case 3 -> {
                    System.out.println();
                    DoctorView.printDoctors(hospitalManagement.getDoctorsList());
                }
                case 4 -> {
                    System.out.println();
                    int id = ReadingInputs.readInt(scanner, "Enter doctor id: ");
                    hospitalManagement.deleteDoctorById(id);
                }
                case 5 -> {
                    System.out.println("Doctor session closed");
                    System.out.println();
                    flag = false;
                }
                default -> System.out.println("Wrong choice input");
            }
        }
    }
}
