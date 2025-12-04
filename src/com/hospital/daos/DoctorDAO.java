package com.hospital.daos;
import com.hospital.pojos.Doctor;
import com.hospital.utils.DatabaseConnector;
import com.hospital.utils.DoctorSupplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDAO {
    public boolean addDoctorToDB(Doctor doctor){
        String sql = "INSERT INTO doctors (name,last_name,major,gender) VALUES (?,?,?,?)";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1,doctor.getName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getMajor().toString().toUpperCase());
            stmt.setString(4, doctor.getGender().toString().toUpperCase());
            return stmt.executeUpdate() > 0;
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }


    public boolean updateDoctorName(Doctor doctor, String newName){
        String sql = "UPDATE doctors SET name = ? WHERE id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, newName);
            stmt.setInt(2, doctor.getId());
            doctor.setName(newName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }


    public boolean deleteDoctorById(int id){
        String sql = "DELETE FROM doctors WHERE id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1,id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }


    public Optional<Doctor> getDoctorByName(String doctorName){
        String sql = "SELECT * FROM doctors WHERE name = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, doctorName);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                return Optional.of(DoctorSupplier.getDoctorViaResultSet(resultSet));
            }
            return Optional.empty();
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }

    public Optional<Doctor> getDoctorById(int id){
        String sql = "SELECT * FROM doctors WHERE id = ?";
        try(Connection con = DatabaseConnector.getDatabaseConnection(); PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                return Optional.of(DoctorSupplier.getDoctorViaResultSet(resultSet));
            }
            return Optional.empty();
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }


    public List<Doctor> getListOfDoctors(){
        String sql = "SELECT * FROM doctors";
        List<Doctor> doctors = new ArrayList<>();
        try(Connection con = DatabaseConnector.getDatabaseConnection(); Statement stmt = con.createStatement()){
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                Doctor foundDoctor = DoctorSupplier.getDoctorViaResultSet(resultSet);
                doctors.add(foundDoctor);
            }
            return doctors;
        } catch (SQLException e){
            throw new RuntimeException("Error in db", e);
        }
    }
}
