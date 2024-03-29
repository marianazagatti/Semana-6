package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection startConection(){
        try {
            String url = "jdbc:mysql://localhost:3306/farmacia";
            return DriverManager.getConnection(url, "root", "");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
