package com.hospital.daos;

import com.hospital.pojos.Patient;
import com.hospital.utils.DatabaseConnector;
import com.hospital.utils.PatientSupplier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientDAO {
    public void addPatientToDB(Patient patient){
        String sql = "INSERT INTO patients(name,gender,birth_date,middle_name,last_name) VALUES(?,?,?,?,?)";
        try (Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getGender().toString());
            stmt.setDate(3, Date.valueOf(patient.getBirthDate()));
            stmt.setString(4, patient.getMiddleName());
            stmt.setString(5, patient.getLastName());
            stmt.executeUpdate();
            System.out.println("Patient has been added to db !");
        } catch (SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }



    public Optional<Patient> getPatientById(int id){
        String sql = "SELECT * FROM patients WHERE id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql);) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return Optional.of(PatientSupplier.getPatientViaResultSet(resultSet));
            }
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
        return Optional.empty();
    }


    public Optional<Patient> findPatientByName(String patientName){
        String sql = "SELECT * FROM patients WHERE name = ?";
        try(Connection connection = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, patientName);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                return Optional.of(PatientSupplier.getPatientViaResultSet(resultSet));
            }
        } catch(SQLException e){
            throw new RuntimeException("Error in db",e);
        }
        return Optional.empty();
    }


    public boolean updatePatientName(Patient patient, String newName){
        String sql = "UPDATE patients SET name = ? WHERE id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, newName);
            stmt.setInt(2,patient.getId());
            patient.setName(newName);
            return stmt.executeUpdate() > 0;

        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }

    public List<Patient> getPatientsList(){
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); Statement stmt = con.createStatement()){
            ResultSet resultedPatients = stmt.executeQuery(sql);
            while(resultedPatients.next()){
                Patient patientToAdd = PatientSupplier.getPatientViaResultSet(resultedPatients);
                patients.add(patientToAdd);
            }
            return patients;
        } catch (SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }

    public boolean deletePatientById(Patient patient){
        String sql = "DELETE FROM patients WHERE id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1,patient.getId());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){
            throw new RuntimeException("Error in the database");
        }
    }
}
