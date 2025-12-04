package com.hospital.view;

import com.hospital.pojos.Patient;

import java.util.List;

public class PatientView {
    public static void printPatientMenu(){
        System.out.println("""
                Patient operations: 
                \t 1. Admit patient
                \t 2. Update patient name
                \t 3. View Patients
                \t 4. Delete patient(by id)
                \t 5. Back to main menu
                """);
    }

    public static void printPatients(List<Patient> patientList){
        if(patientList.isEmpty()){
            System.out.println("There are no patients to print (: ");
            return;
        }
        patientList.forEach(System.out::println);
    }

}
