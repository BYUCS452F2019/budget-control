package com.budgetControlGroup.budgetControl.budgetController;

import com.budgetControlGroup.budgetControl.Models.Budget;
import com.budgetControlGroup.budgetControl.dataAccess.Dynamo.BudgetDao;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/budget")

@RestController
public class BudgetController {

    private String url = "jdbc:postgresql:budgetcontrol";
    private final String user = "postgres";
    private final String password = "jmcBudget452";
    private BudgetDao budgetDao = new BudgetDao();

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "")
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
            connection.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/id")
    public Budget singleBudget(@RequestParam(value="budget_id", defaultValue="1") String budget_id) {
        Budget result = null;
        String query = "SELECT * FROM Budget WHERE budget_id = %s;";

        try {
            Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(query, budget_id));

            if (resultSet.next()) {
                result = new Budget(resultSet.getInt("budget_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("start_date"),
                        resultSet.getString("end_date"),
                        resultSet.getString("total_income"),
                        resultSet.getString("total_expense"),
                        resultSet.getString("description"));
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/all_budgets")
    public List<Budget> getAllBudgetsForUser(@RequestParam(value="user_id") String user_id) {
//        List<Budget> result = new ArrayList<>();
//        String query = "SELECT * FROM Budget WHERE user_id = %s;";
//
//        try {
//            Connection connection = connect();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(String.format(query, user_id));
//
//            while (resultSet.next()) {
//                Budget budget = new Budget(resultSet.getInt("budget_id"),
//                        resultSet.getString("name"),
//                        resultSet.getInt("user_id"),
//                        resultSet.getString("start_date"),
//                        resultSet.getString("end_date"),
//                        resultSet.getString("total_income"),
//                        resultSet.getString("total_expense"),
//                        resultSet.getString("description"));
//                result.add(budget);
//            }
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }

        List<Budget> result = null;

        try {
            result = budgetDao.getBudgetsForUser(Integer.parseInt(user_id));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return result;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/user_id")
    public Budget budgetByUserId(@RequestParam(value="user_id") String user_id) {
//        Budget result = null;
//        String query = "SELECT * FROM Budget WHERE user_id = %s;";
//
//        try {
//            Connection connection = connect();
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(String.format(query, user_id));
//
//            if (resultSet.next()) {
//                result = new Budget(resultSet.getInt("budget_id"),
//                        resultSet.getString("name"),
//                        resultSet.getInt("user_id"),
//                        resultSet.getString("start_date"),
//                        resultSet.getString("end_date"),
//                        resultSet.getString("total_income"),
//                        resultSet.getString("total_expense"),
//                        resultSet.getString("description"));
//            }
//        } catch (Exception ex) {
//            System.err.println(ex.getMessage());
//        }

        List<Budget> result = null;
        try {
            result = budgetDao.getBudgetsForUser(Integer.parseInt(user_id));
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return result.get(0);
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
