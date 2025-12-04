package com.hospital.utils;
import com.hospital.Enums.Genders;
import com.hospital.Enums.Majors;
import com.hospital.pojos.Appointment;
import com.hospital.pojos.Doctor;
import com.hospital.pojos.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ReadingInputs {
    public static String readString(Scanner scanner, String message){
        System.out.println(message);
        return scanner.nextLine();
    }


    public static int readInt(Scanner scanner, String message){
        System.out.println(message);
        int number = 0;
        number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }


    public static Patient readPatient(Scanner scanner){
        System.out.println("Enter patient name: ");
        String name = Validations.checkNotNull(scanner.nextLine(), "No null names allowed !");
        System.out.println("Enter patient last name: ");
        String lastName = Validations.checkNotNull(scanner.nextLine(), "Last name is mandatory");
        System.out.println("Enter patient middle name: ");
        String middleName = scanner.nextLine();
        System.out.println("Enter patient gender: ");
        String gender = Validations.checkNotNull(scanner.nextLine(), "Gender must be entered");
        System.out.println("Enter patient birth date: yy-mm-dd");
        String birthDate = Validations.checkNotNull(scanner.nextLine(), "Birth date must be entered");
        return new Patient(name, Genders.valueOf(gender.toUpperCase()), LocalDate.parse(birthDate), middleName, lastName);
    }


    public static Doctor readDoctor(Scanner scanner){
        System.out.println("Enter doctor name: ");
        String name = Validations.checkNotNull(scanner.nextLine(), "Doctor name must not be null");
        System.out.println("Enter doctor last name: ");
        String lastName = Validations.checkNotNull(scanner.nextLine(), "Doctor's last name is mandatory");
        System.out.println("Enter doctor major: ");
        String major = Validations.checkNotNull(scanner.nextLine().toUpperCase(), "Doctor must have a major");
        System.out.println("Enter doctor gender: ");
        String gender = Validations.checkNotNull(scanner.nextLine().toUpperCase(), "Gender must be selected");
        return new Doctor(name,lastName, Majors.valueOf(major),Genders.valueOf(gender));
    }


    public static Appointment readAppointment(Scanner scanner){
        LocalTime appointmentTime = Validations.checkNotNull(readLocalTime(scanner), "Must enter a valid time");
        LocalDate appointmentDate= Validations.checkNotNull(readLocalDate(scanner), "Must enter a valid date");
        int patient_id = ReadingInputs.readInt(scanner, "Enter patient's id for the appointment (id must already exist)");
        int doctor_id = ReadingInputs.readInt(scanner, "Enter doctor's id for the appointment (id must already exist");
        return new Appointment(appointmentDate, appointmentTime, patient_id,doctor_id);
    }

    public static LocalTime readLocalTime(Scanner scanner){
        System.out.println("Please enter time: hh-mm-ss");
        return LocalTime.parse(scanner.nextLine());
    }

    public static LocalDate readLocalDate(Scanner scanner){
        System.out.println("Please enter date: yy-mm-dd");
        return LocalDate.parse(scanner.nextLine());
    }
}
