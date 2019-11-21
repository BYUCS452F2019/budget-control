package com.budgetControlGroup.budgetControl.utils;

import com.budgetControlGroup.budgetControl.dataAccess.DBConnection;
import com.budgetControlGroup.budgetControl.database.PostgresConnection;
import com.budgetControlGroup.budgetControl.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UserUtils {
  PostgresConnection postgresConnection;

  @Autowired
  public UserUtils(PostgresConnection postgresConnection) {
    this.postgresConnection = postgresConnection;
  }

  public boolean exists(String username, String password) {
    Connection c = null;
    try {
      c = DBConnection.connect();
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }

    String userExists = "Select * " +
        "From users u " +
        "Where u.username = '"+username+"' and u.password = '"+password+"'";
    try {
      Statement stmt = null;
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(userExists);
      boolean next = rs.next();
      stmt.close();
      c.close();
      return next;
    }
    catch (SQLException ex) {
      System.out.println(ex.getMessage());
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public boolean exists(String username) {
    Connection c = null;
    try {
      c = DBConnection.connect();
    }catch(SQLException e){
      System.out.println(e.getMessage());
    }

    String userExists = "Select * " +
        "From users u " +
        "Where u.username = '"+username+"'";
    try {
      Statement stmt = null;
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(userExists);
      boolean next = rs.next();
      stmt.close();
      c.close();
      return next;
    }
    catch (SQLException ex) {
      System.out.println(ex.getMessage());
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  //"user_id SERIAL PRIMARY KEY, " +
  //            "first_name VARCHAR(25), " +
  //            "last_name VARCHAR(25), " +
  //            "email VARCHAR(50), " +
  //            "username VARCHAR(15), " +
  //            "password VARCHAR(100), " +
  //            "last_login DATE, " +
  //            "date_created DATE" +
  //            ");";

  public User getUser(String username, String password) {
    Connection c = null;
    try {
      c = DBConnection.connect();
    } catch(SQLException e){
      System.out.println("Problem connecting");
    }

    String userExists = "Select * " +
        "From users u " +
        "Where u.username = '"+username+"' and u.password = '"+password+"'";
    try {
      Statement stmt = null;
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(userExists);
      rs.next();
      User user = new User(rs.getInt(1),
              rs.getString(5),
              rs.getString(2),
              rs.getString(3),
              rs.getString(4),
              rs.getDate(7),
              rs.getDate(8),
              null);
      stmt.close();
      c.close();
      return user;
    }
    catch (SQLException ex) {
      System.out.println(ex.getMessage());
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
