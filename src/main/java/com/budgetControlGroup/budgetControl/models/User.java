package com.budgetControlGroup.budgetControl.models;

import java.util.Date;


public class User {
  int userId;
  String username;
  String firstName;
  String lastName;
  String email;
  Date lastLogin;
  Date dateCreated;

  public User() {
  }

  public int getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public Date getLastLogin() {
    return lastLogin;
  }
}
