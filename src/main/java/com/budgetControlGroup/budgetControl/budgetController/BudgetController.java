package com.budgetControlGroup.budgetControl.budgetController;

import com.budgetControlGroup.budgetControl.Models.Budget;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BudgetController {

    private String url = "jdbc:postgresql://localhost/budgetcontrol";
    private final String user = "postgres";
    private final String password = "jmcBudget452";

    @RequestMapping("/budget")
    public List<Budget> budget() {
        List<Budget> result = new ArrayList<>();
        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM budget;");

            while (resultSet.next()) {
                result.add(new Budget(resultSet.getInt("budget_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("start_date"),
                        resultSet.getString("end_date"),
                        resultSet.getString("total_income"),
                        resultSet.getString("total_expense"),
                        resultSet.getString("description")));
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    private Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return conn;
    }
}
