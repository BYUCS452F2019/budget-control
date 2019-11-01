package com.budgetControlGroup.budgetControl.workflows;

import com.budgetControlGroup.budgetControl.database.PostgresConnection;
import com.budgetControlGroup.budgetControl.Models.User;
import com.budgetControlGroup.budgetControl.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class LoginWorkflow {
  private UserUtils userUtils;

  @Autowired
  public LoginWorkflow(UserUtils userUtils) {
    this.userUtils = userUtils;
  }

  public User login(String username, String password) {
    if(!userUtils.exists(username,password)) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Invalid username or password");
    }
    return userUtils.getUser(username,password);
  }
}
