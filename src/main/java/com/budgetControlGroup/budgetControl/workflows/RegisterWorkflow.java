package com.budgetControlGroup.budgetControl.workflows;

import com.budgetControlGroup.budgetControl.dataAccess.DBConnection;
import com.budgetControlGroup.budgetControl.database.PostgresConnection;
import com.budgetControlGroup.budgetControl.Models.User;
import com.budgetControlGroup.budgetControl.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.*;
import java.util.Calendar;

@Service
public class RegisterWorkflow {
  private PostgresConnection postgresConnection;
  private UserUtils checkUserExistence;


  @Autowired
  public RegisterWorkflow(PostgresConnection postgresConnection, UserUtils checkUserExistence) {
    this.postgresConnection = postgresConnection;
    this.checkUserExistence = checkUserExistence;
  }

  public User register(User user) {
    System.out.println(user);
//    if(checkUserExistence.exists(user.getUsername())) {
//      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"User already exists");
//    }
    user.setUserId(insert(user));
    return user;
  }

  private int insert(User user) {
    //Connection c = postgresConnection.getConnection();
    Connection c = null;
    try {
      c = DBConnection.connect();
    } catch(SQLException e){
      System.out.println("Couldn't connect");
    }
    String q1 = "insert into users (first_name, last_name, email, username, password, last_login, date_created) values('" +user.getFirstName()+ "', '" +
        user.getLastName()+ "', '" +
        user.getEmail()+ "', '" +
        user.getUsername()+ "', '" +
        user.getPassword()+ "', '" +
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
          int someInt = set.getInt(1);
          c.close();
          return someInt;
        }
        else {
          c.close();
          return -1;
        }
      }



    }
    catch(Exception e) {
      System.out.println(e.getMessage());
      throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
