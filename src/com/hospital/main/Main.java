package com.hospital.main;

import com.hospital.controllers.AppointmentController;
import com.hospital.controllers.DoctorController;
import com.hospital.controllers.HospitalController;
import com.hospital.controllers.PatientController;
import com.hospital.daos.AppointmentDAO;
import com.hospital.daos.DoctorDAO;
import com.hospital.daos.PatientDAO;
import com.hospital.pojos.Appointment;
import com.hospital.utils.DatabaseConnector;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector.getDatabaseConnection();
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        HospitalManagement hospitalManagement = new HospitalManagement(patientDAO, doctorDAO, appointmentDAO);
        PatientController patientController = new PatientController(hospitalManagement);
        DoctorController doctorController = new DoctorController(hospitalManagement);
        AppointmentController appointmentController = new AppointmentController(hospitalManagement);
        HospitalController HMS = new HospitalController(patientController, doctorController, appointmentController);
        HMS.startHospitalSession();
    }
}
