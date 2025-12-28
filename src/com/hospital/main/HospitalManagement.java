package com.hospital.main;

import com.hospital.daos.AppointmentDAO;
import com.hospital.daos.DoctorDAO;
import com.hospital.daos.PatientDAO;
import com.hospital.pojos.Appointment;
import com.hospital.pojos.Doctor;
import com.hospital.pojos.Patient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class HospitalManagement {
    private final PatientDAO patientDAO;
    private final DoctorDAO doctorDAO;
    private final AppointmentDAO appointmentDAO;
    public HospitalManagement(PatientDAO patientDAO, DoctorDAO doctorDAO, AppointmentDAO appointmentDAO) {
        this.patientDAO = patientDAO;
        this.doctorDAO = doctorDAO;
        this.appointmentDAO = appointmentDAO;
    }

    public void admitPatient(Patient patient){
        Optional<Patient> patientOptional = patientDAO.findPatientByName(patient.getName());
        if(patientOptional.isPresent()){
            System.out.println("user already in db");
            return;
        }
        patientDAO.addPatientToDB(patient);
    }



    public void updatePatientName(int patientId, String newName){
        Optional<Patient> foundPatient = patientDAO.getPatientById(patientId);
        if(foundPatient.isPresent()){
            boolean updated = patientDAO.updatePatientName(foundPatient.get(), newName);
            if(updated){
                System.out.println("Patient has been updated");
            } else{
                System.out.println("Error in db");
            }
            return;
        }
        System.out.println("Patient does not exist");
    }

    public void deletePatientById(int id){
        Optional<Patient> foundPatient = patientDAO.getPatientById(id);
        if(foundPatient.isPresent()){
            boolean deletedPatient = patientDAO.deletePatientById(foundPatient.get());
            if(deletedPatient) {
                System.out.println("patient deleted");
            } else{
                System.out.println("Error in db");
            }
            return;
        }
        System.out.println("patient not found");
    }


    public List<Patient> getPatientsList(){
        return patientDAO.getPatientsList();
    }

    public void addDoctor(Doctor doctor){
        Optional<Doctor> foundDoctor = doctorDAO.getDoctorByName(doctor.getName());
        if(foundDoctor.isEmpty()){
            boolean added = doctorDAO.addDoctorToDB(doctor);
            if(added){
                System.out.println("Doctor has been added to db");
                return;
            } else
                System.out.println("Error in db");
            return;
        }
        System.out.println("Doctor already in db !");
    }


    public void updateDoctorName(int doctorId, String newName){
        Optional<Doctor> foundDoctor = doctorDAO.getDoctorById(doctorId);
        if(foundDoctor.isPresent()){
            boolean updatedName = doctorDAO.updateDoctorName(foundDoctor.get(), newName);
            if(updatedName){
                System.out.println("Doctor name has been updated");
                return;
            } else
                System.out.println("Error in db");
            return;
        }
        System.out.println("Error in db");
    }

    public void deleteDoctorById(int id){
        Optional<Doctor> doctor = doctorDAO.getDoctorById(id);
        if(doctor.isPresent()){
            boolean deleted = doctorDAO.deleteDoctorById(id);
            System.out.println((deleted) ? "Doctor deleted success" : "Doctor failed to delete");
            return;
        }
        System.out.println("Error in db");
    }


    public List<Doctor> getDoctorsList(){
        return doctorDAO.getListOfDoctors();
    }


    public void bookAppointment(Appointment appointment){
        if (!appointmentDAO.isBooked(appointment.getAppointmentTime(), appointment.getAppointmentDate(), appointment.getPatient_id(), appointment.getDoctor_id())) {
               boolean newAppointment = appointmentDAO.bookAppointment(appointment);
               System.out.println((newAppointment) ? "Appointment added to db" : "Error in db");
               return;
        }
        System.out.println("Appointment already exists");
    }

    public void updateAppointmentTime(int appointmentId, LocalTime newTime){
        Optional<Appointment> appointment = appointmentDAO.getAppointmentById(appointmentId);
        if(appointment.isPresent()) {
            if (appointmentDAO.isBooked(appointment.get().getAppointmentTime(), appointment.get().getAppointmentDate(), appointment.get().getPatient_id(), appointment.get().getDoctor_id())) {
                boolean updated = appointmentDAO.updateAppointmentTime(appointment.get(), newTime);
                System.out.println((updated) ? "Appointment time updated success" : "Error in db");
                return;
            }
            System.out.println("Error in db");
        }
        System.out.println("Appointment with this id is not booked");
    }

    public void updateAppointmentDate(int appointmentId, LocalDate newDate){
        Optional<Appointment> appointment = appointmentDAO.getAppointmentById(appointmentId);
        if(appointment.isPresent()) {
            if (appointmentDAO.isBooked(appointment.get().getAppointmentTime(), appointment.get().getAppointmentDate(), appointment.get().getPatient_id(), appointment.get().getDoctor_id())) {
                boolean updated = appointmentDAO.updateAppointmentDate(appointment.get(), newDate);
                System.out.println((updated) ? "Appointment time updated success" : "Error in db");
                return;
            }
            System.out.println("Error in db");
        }
        System.out.println("Appointment with this time is not booked");
    }


    public void deleteAppointmentById(int appointmentId){
        Optional<Appointment> foundAppointment = appointmentDAO.getAppointmentById(appointmentId);
        if(foundAppointment.isPresent()){
            boolean deleted = appointmentDAO.deleteAppointment(foundAppointment.get());
            System.out.println((deleted) ? "Appointment has been deleted" : "Error in db");
            return;
        }
        System.out.println("Appointment with this id is not found");
    }

    public List<Appointment> getAppointmentList(){
        return appointmentDAO.getListOfAppointments();
    }

    public List<Appointment> getAppointmentsForPatient(int patient_id){
        return appointmentDAO.getAppointmentsByPatientId(patient_id);
    }


    public List<Doctor> getDoctorsByMajor(String major){
        return doctorDAO.getDoctorsByMajor(major);
    }
}
