package com.budgetControlGroup.budgetControl.database;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.*;

@Service
public class PostgresConnection {
  //private final String url = "jdbc:postgresql://localhost/budgetcontrol";
  private final String url = "jdbc:postgresql:budgetcontrol";
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

  boolean isConnected() {
    String connect = "Select 1 " ;
    try {
      Statement stmt = null;
      stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(connect);
    }
    catch (SQLException ex) {
      return false;
    }
    return true;
  }

  public Connection getConnection() {
    if(!isConnected()) {
      connect();
    }
    return connection;
  }
}
