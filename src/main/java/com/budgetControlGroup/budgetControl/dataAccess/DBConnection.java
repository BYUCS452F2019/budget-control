package com.budgetControlGroup.budgetControl.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:postgresql:budgetcontrol";
    private static final String user = "postgres";
    private static final String password = "jmcBudget452";

    public static Connection connect() throws SQLException {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!");
        } catch(SQLException e){
            System.err.println(e.getMessage());
            throw new SQLException("Couldn't connect to database!!");
        }
        return conn;
    }
}
