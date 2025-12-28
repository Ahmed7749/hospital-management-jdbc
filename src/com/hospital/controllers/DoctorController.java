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
                    boolean added = hospitalManagement.addDoctor(ReadingInputs.readDoctor(scanner));
                    System.out.println((added) ? "Added successfully" : "Doctor already exists");
                }
                case 2 -> {
                    System.out.println();
                    int doctorId = ReadingInputs.readInt(scanner, "Enter doctor id: ");
                    scanner.nextLine();
                    String doctorName = ReadingInputs.readString(scanner,"Enter new name: ");
                    boolean updated = hospitalManagement.updateDoctorName(doctorId,doctorName);
                    System.out.println((updated) ? "Doctor name updated" : "Error could not update name");
                }
                case 3 -> {
                    System.out.println();
                    DoctorView.printDoctors(hospitalManagement.getDoctorsList());
                }
                case 4 -> {
                    System.out.println();
                    int id = ReadingInputs.readInt(scanner, "Enter doctor id: ");
                    boolean deleted = hospitalManagement.deleteDoctorById(id);
                    System.out.println((deleted) ? "Doctor deleted successfully" : "Failed to delete");
                }
                case 5 -> {
                    System.out.println();
                    String major = ReadingInputs.readString(scanner, "Enter the doctor major: (surgeon, general)");
                    DoctorView.printDoctors(hospitalManagement.getDoctorsByMajor(major));
                }

                case 6 -> {
                    System.out.println("Doctor session closed");
                    flag = false;
                }
                default -> System.out.println("Wrong choice input");
            }
        }
    }
}
