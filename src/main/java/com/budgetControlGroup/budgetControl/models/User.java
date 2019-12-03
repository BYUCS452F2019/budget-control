package com.budgetControlGroup.budgetControl.Models;

import java.util.Date;


public class User {
  int userId;
  String username;
  String firstName;
  String lastName;
  String email;
  Date lastLogin;
  Date dateCreated;
  String password;

  public User() {
  }

  public User(int userId, String username, String firstName, String lastName, String email, Date lastLogin, Date dateCreated, String password) {
    this.userId = userId;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.lastLogin = lastLogin;
    this.dateCreated = dateCreated;
    this.password = password;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public Date getLastLogin() {
    return lastLogin;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId=" + userId +
        ", username='" + username + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", lastLogin=" + lastLogin +
        ", dateCreated=" + dateCreated +
        ", password='" + password + '\'' +
        '}';
  }
}
