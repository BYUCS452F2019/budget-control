package com.budgetControlGroup.budgetControl.workflows;

import com.budgetControlGroup.budgetControl.database.PostgresConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginWorkflow {
  private PostgresConnection postgresConnection;

  @Autowired
  public LoginWorkflow(PostgresConnection postgresConnection) {
    this.postgresConnection = postgresConnection;
  }

  public String login(String username, String password) {
    //check if user exists
    //if it doesnt, return empty string
    //if it does, return user id
    return "";
  }
}
