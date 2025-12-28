package com.hospital.daos;

import com.hospital.utils.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class GenericDAO<T>{
    protected boolean executeUpdate(String sql, Object... parameters){
        Connection con = null;
        try{
            con = DatabaseConnector.getDatabaseConnection();
            try(PreparedStatement stmt = con.prepareStatement(sql)){
                for(int i = 0; i < parameters.length; i++){
                    stmt.setObject(i + 1, parameters[i]);
                }

                return stmt.executeUpdate() > 0;
            }
        } catch(SQLException e){
            throw new RuntimeException("Error in db", e);
        } finally {
            DatabaseConnector.returnConnection(con);
        }
    }
}
