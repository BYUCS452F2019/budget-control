package com.budgetControlGroup.budgetControl.controllers;

import com.budgetControlGroup.budgetControl.models.User;
import com.budgetControlGroup.budgetControl.workflows.LoginWorkflow;
import com.budgetControlGroup.budgetControl.workflows.RegisterWorkflow;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserController {
  private RegisterWorkflow registerWorkflow;
  private LoginWorkflow loginWorkflow;

  @RequestMapping("/register")
  public String register(@RequestHeader("password") String password,
                         @RequestBody User user) {
    return registerWorkflow.register(user,password);
  }

  @RequestMapping("/user")
  public String login(@RequestHeader("username") String username,
                      @RequestHeader("password") String password) {
    return loginWorkflow.login(username,password);
  }
}
