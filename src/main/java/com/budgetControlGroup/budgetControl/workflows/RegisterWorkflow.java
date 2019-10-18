package com.budgetControlGroup.budgetControl.workflows;

import com.budgetControlGroup.budgetControl.models.User;
import org.springframework.stereotype.Service;

@Service
public class RegisterWorkflow {


  public String register(User username, String password) {
    //check is username is in use
    //if it does, return empty string
    //if it doesnt, create user, then return id
    return "";
  }
}
