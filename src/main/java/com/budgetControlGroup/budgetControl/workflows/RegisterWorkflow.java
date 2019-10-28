package com.budgetControlGroup.budgetControl.workflows;

import com.budgetControlGroup.budgetControl.database.PostgresConnection;
import com.budgetControlGroup.budgetControl.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

@Service
public class RegisterWorkflow {
  private PostgresConnection postgresConnection;



  @Autowired
  public RegisterWorkflow(PostgresConnection postgresConnection) {
    this.postgresConnection = postgresConnection;
  }

  public Integer register(User user, String password) {
    if(exists(user.getUsername())) {
      return -1;
    }

    return insert(user,password);
  }

  private int insert(User user, String password) {
    Connection c = postgresConnection.getConnection();
    String q1 = "insert into userid values('" +user.getFirstName()+ "', '" +
        user.getLastName()+ "', '" +
        user.getEmail()+ "', '" +
        user.getUsername()+ "', '" +
        password+ "', '" +
        Calendar.getInstance().getTime()+ "', '"  +
        Calendar.getInstance().getTime()+ "');";
    String q2 = "SELECT SCOPE_IDENTITY();";
    try {
      Statement stmt = c.createStatement();

      // Inserting data in database

      int x = stmt.executeUpdate(q1);
      if (x <= 0) {
        throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
      }

      try(ResultSet set = stmt.getGeneratedKeys()) {
        if(set.next())  {
          c.close();
          return set.getInt(1);
        }
        else {
          c.close();
          return -1;
        }
      }



    }
    catch(Exception e) {
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private boolean exists(String username) {
    Connection c = postgresConnection.getConnection();

    String userExists = "Select 1 " +
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
