package com.hospital.daos;

import com.hospital.pojos.Appointment;
import com.hospital.utils.AppointmentSupplier;
import com.hospital.utils.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentDAO {
    public boolean bookAppointment(Appointment appointment){
        String sql = "INSERT INTO appointments (appointment_date, appointment_time,patient_id,doctor_id) VALUES (?,?,?,?)";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setDate(1, Date.valueOf(appointment.getAppointmentDate()));
            stmt.setTime(2, Time.valueOf(appointment.getAppointmentTime()));
            stmt.setInt(3, appointment.getPatient_id());
            stmt.setInt(4, appointment.getDoctor_id());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }

    public Optional<Appointment> getAppointmentById(int id){
        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                return Optional.of(AppointmentSupplier.getAppointmentViaResultSet(resultSet));
            }
            return Optional.empty();
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId){
        String sql = "SELECT * FROM appointments WHERE patient_id = ?";
        List<Appointment> appointments = new ArrayList<>();
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1,patientId);
            ResultSet resultSet = stmt.executeQuery();
            while(resultSet.next()){
                appointments.add(AppointmentSupplier.getAppointmentViaResultSet(resultSet));
            }
            return appointments;
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }



    public Optional<Appointment> getAppointmentByTime(LocalTime time){
        String sql = "SELECT * FROM appointments WHERE appointment_time = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setTime(1,Time.valueOf(time));
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                return Optional.of(AppointmentSupplier.getAppointmentViaResultSet(resultSet));
            }
            return Optional.empty();
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }


    public boolean isBooked(LocalTime time,LocalDate date,int patient_id, int doctor_id){
        String sql = "SELECT * FROM appointments WHERE appointment_time = ? AND patient_id = ? AND appointment_date = ? AND doctor_id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setTime(1, Time.valueOf(time));
            stmt.setInt(2,patient_id);
            stmt.setDate(3, Date.valueOf(date));
            stmt.setInt(4, doctor_id);
            return stmt.executeQuery().next();
        } catch(SQLException e){
            throw new RuntimeException("Error in db",e);
        }
    }


    public boolean deleteAppointment(Appointment appointment){
        String sql = "DELETE FROM appointments WHERE appointment_id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1,appointment.getId());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }


    public boolean updateAppointmentTime(Appointment appointment,LocalTime newTime){
        String sql = "UPDATE appointments SET appointment_time = ? WHERE appointment_id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setTime(1, Time.valueOf(newTime));
            stmt.setInt(2, appointment.getId());
            appointment.setAppointmentTime(newTime);
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }


    public boolean updateAppointmentDate(Appointment appointment, LocalDate newDate){
        String sql = "UPDATE appointments SET appointment_date = ? WHERE appointment_id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setDate(1, Date.valueOf(newDate));
            stmt.setInt(2, appointment.getId());
            appointment.setAppointmentDate(newDate);
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }


    public List<Appointment> getListOfAppointments(){
        String sql = "SELECT * FROM appointments";
        List<Appointment> appointments = new ArrayList<>();
        try(Connection con = DatabaseConnector.getDatabaseConnection(); Statement stmt = con.createStatement()){
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                appointments.add(AppointmentSupplier.getAppointmentViaResultSet(resultSet));
            }
            return appointments;
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }
}
