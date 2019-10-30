package com.budgetControlGroup.budgetControl.utils;

import com.budgetControlGroup.budgetControl.database.PostgresConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class CheckUserExistence {
  PostgresConnection postgresConnection;

  @Autowired
  public CheckUserExistence(PostgresConnection postgresConnection) {
    this.postgresConnection = postgresConnection;
  }

  public boolean exists(String username, String password) {
    Connection c = postgresConnection.getConnection();

    String userExists = "Select * " +
        "From users u" +
        "Where u.username = '"+username+"' and u.password = '"+password+"'";
    try {
      Statement stmt = null;
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(userExists);

      stmt.close();
      c.close();
      return rs.next();
    }
    catch (SQLException ex) {
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public boolean exists(String username) {
    Connection c = postgresConnection.getConnection();

    String userExists = "Select * " +
        "From users u" +
        "Where u.username = '"+username+"'";
    try {
      Statement stmt = null;
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(userExists);

      stmt.close();
      c.close();
      return rs.next();
    }
    catch (SQLException ex) {
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
