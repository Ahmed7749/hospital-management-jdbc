package com.hospital.view;

import com.hospital.pojos.Doctor;

import java.util.List;

public class DoctorView {
    public static void printDoctorMenu(){
        System.out.println("""
                1.\t add doctor
                2.\t update doctor name
                3.\t view doctors
                4.\t delete doctor(by id)
                5.\t back to main menu
                """);
    }

    public static void printDoctors(List<Doctor> doctors){
        if(doctors.isEmpty()){
            System.out.println("No doctors to print ): ");
            return;
        }
        doctors.forEach(System.out::println);
    }
}
