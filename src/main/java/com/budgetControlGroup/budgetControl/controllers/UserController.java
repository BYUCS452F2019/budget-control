package com.budgetControlGroup.budgetControl.controllers;

import com.budgetControlGroup.budgetControl.models.User;
import com.budgetControlGroup.budgetControl.workflows.LoginWorkflow;
import com.budgetControlGroup.budgetControl.workflows.RegisterWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
  private RegisterWorkflow registerWorkflow;
  private LoginWorkflow loginWorkflow;

  @Autowired
  public UserController(RegisterWorkflow registerWorkflow, LoginWorkflow loginWorkflow) {
    this.registerWorkflow = registerWorkflow;
    this.loginWorkflow = loginWorkflow;
  }

  @RequestMapping(method = RequestMethod.PUT, value="/register")
  public User register(@RequestBody User user) {
    return registerWorkflow.register(user);
  }

  @RequestMapping(method = RequestMethod.GET, value="/login")
  public User login(@RequestHeader("username") String username,
                      @RequestHeader("password") String password) {
    return loginWorkflow.login(username,password);
  }
}
