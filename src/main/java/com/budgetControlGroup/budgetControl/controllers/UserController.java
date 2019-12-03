package com.budgetControlGroup.budgetControl.controllers;

import com.budgetControlGroup.budgetControl.Models.User;
import com.budgetControlGroup.budgetControl.dataAccess.Dynamo.UserDao;
import com.budgetControlGroup.budgetControl.workflows.LoginWorkflow;
import com.budgetControlGroup.budgetControl.workflows.RegisterWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
  private RegisterWorkflow registerWorkflow;
  private LoginWorkflow loginWorkflow;
  private UserDao userDao = new UserDao();

//  @Autowired
//  public UserController(RegisterWorkflow registerWorkflow, LoginWorkflow loginWorkflow, UserDao userDao) {
//    this.registerWorkflow = registerWorkflow;
//    this.loginWorkflow = loginWorkflow;
//    this.userDao = userDao;
//  }



  @CrossOrigin(origins = "*")
  @RequestMapping(method = RequestMethod.PUT, value="/register")
  public User register(@RequestBody User user) {
    return userDao.addUser(user);
//    registerWorkflow.register(user);
  }

  @CrossOrigin(origins = "*")
  @RequestMapping(method = RequestMethod.GET, value="/login")
  public User login(@RequestParam(value="username") String username,
                      @RequestParam(value="password") String password) {
    return userDao.login(new User(-1,username,null,null,null,null,null,password));
  }
}
