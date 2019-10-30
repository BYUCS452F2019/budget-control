package com.budgetControlGroup.budgetControl;

import com.budgetControlGroup.budgetControl.Models.Transaction;
import com.budgetControlGroup.budgetControl.dataAccess.TransactionDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class BudgetControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetControlApplication.class, args);
	}

}
