package com.budgetControlGroup.budgetControl;

import com.budgetControlGroup.budgetControl.Models.User;
import com.budgetControlGroup.budgetControl.dataAccess.Dynamo.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetControlApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void p(){
		UserDao userDao = new UserDao();
		User user = new User(125, "userName", "first", "last", "email", new Date(), new Date(), "pass");

		System.out.println(userDao.addUser(user));
		User login = userDao.login(user);
		assert(login!=null);
	}
}
