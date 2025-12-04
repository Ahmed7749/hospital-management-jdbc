package com.hospital.controllers;

import com.hospital.main.HospitalManagement;

import com.hospital.utils.ReadingInputs;
import com.hospital.view.PatientView;

import java.util.Scanner;

public class PatientController {
    private HospitalManagement hospitalManagement;
    private Scanner scanner = new Scanner(System.in);

    public PatientController(HospitalManagement hospitalManagement) {
        this.hospitalManagement = hospitalManagement;

    }

    public void startPatientSession(){
        boolean flag = true;
        int choice;
        while(flag){
            PatientView.printPatientMenu();
            System.out.println();
            choice = ReadingInputs.readInt(scanner,"Enter your choice: ");
            switch(choice){
                case 1 -> hospitalManagement.admitPatient(ReadingInputs.readPatient(scanner));
                case 2 ->{
                        System.out.println();
                        int id = ReadingInputs.readInt(scanner, "Enter the patient id: ");
                        scanner.nextLine();
                        String newName = ReadingInputs.readString(scanner, "Enter the new name: ");
                        hospitalManagement.updatePatientName(id,newName);
                }
                case 3 -> {
                    System.out.println();
                    PatientView.printPatients(hospitalManagement.getPatientsList());
                }
                case 4 -> {
                        System.out.println();
                        int patientId = ReadingInputs.readInt(scanner, "Enter patient id: ");
                        hospitalManagement.deletePatientById(patientId);
                }
                case 5 -> {
                    System.out.println();
                    System.out.println("Patient session closed");
                    flag = false;
                }
                default -> System.out.println("Invalid choice entered");
            }
        }
    }


}
