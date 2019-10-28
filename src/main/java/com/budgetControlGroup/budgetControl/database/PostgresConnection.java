package com.budgetControlGroup.budgetControl.database;

import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class PostgresConnection {
  private final String url = "jdbc:postgresql://localhost/budgetcontrol";
  private final String user = "postgres";
  private final String password = "jmcBudget452";
  private final Connection connection;

  public PostgresConnection() {
    this.connection = connect();
  }

  private Connection connect(){
    Connection conn = null;
    try{
      conn = DriverManager.getConnection(url, user, password);
      System.out.println("Connected successfully!");
    } catch(SQLException e){
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public Connection getConnection() {
    return connection;
  }
}
