package com.budgetControlGroup.budgetControl.workflows;

import com.budgetControlGroup.budgetControl.Models.Budget;
import com.budgetControlGroup.budgetControl.database.PostgresConnection;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

@Service
public class BudgetCreateWorkflow {
    private PostgresConnection postgresConnection;

    public Budget create_budget(Budget budget) {
        int result = insert(budget);
        return budget;
    }

    public int insert(Budget budget) {
        System.out.println("Here in the insert method for create budget method\n");
        Connection c = postgresConnection.getConnection();
        String q1 = "insert into budget values('" + budget.getName() + "', '" +
                budget.getUser_id() + "', '" +
                budget.getStart_date() + "', '" +
                budget.getEnd_date() + "', '" +
                budget.getTotal_income() + "', '" +
                budget.getTotal_expense() + "', '"  +
                budget.getDescription() + "');";
        try {
            Statement stmt = c.createStatement();

            // Inserting data in database

            int x = stmt.executeUpdate(q1);
            if (x <= 0) {
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                return x;
            }
        }
        catch(Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
