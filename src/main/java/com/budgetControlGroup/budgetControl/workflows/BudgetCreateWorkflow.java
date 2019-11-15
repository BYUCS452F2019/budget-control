package com.budgetControlGroup.budgetControl.workflows;

import com.budgetControlGroup.budgetControl.Models.Budget;
import com.budgetControlGroup.budgetControl.Models.Budget_Item;
import com.budgetControlGroup.budgetControl.Models.Category;
import com.budgetControlGroup.budgetControl.Models.Category_List;
import com.budgetControlGroup.budgetControl.database.PostgresConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

@Service
public class BudgetCreateWorkflow {
    private PostgresConnection postgresConnection;

    @Autowired
    public BudgetCreateWorkflow(PostgresConnection postgresConnection) {
        this.postgresConnection = postgresConnection;
    }

    public Budget create_budget(Budget budget) {
        int result = insert(budget);
        budget.setBudget_id(result);
        return budget;
    }

    public Category_List create_cat(Category_List cats) {
        int user = cats.getUser();
        ArrayList<String> names = cats.getNames();
        ArrayList<String> expenses = cats.getExpenses();
        ArrayList<Budget_Item> budget_items = null;

        for (int i = 0; i < names.size(); i++) {
            Category temp = new Category(names.get(i), user);
            int result = insert_cat(temp);
            System.out.println("In the create cat in workflow after insert cat method result is:");
            System.out.println(result);
            Budget_Item item_b = new Budget_Item(cats.getBudget_id(), result, expenses.get(i));
            int item_result = insert_item(item_b);
            System.out.println("In the create cat in workflow after insert item method result is:");
            System.out.println(item_result);
        }



        return cats;
    }
    public int insert_item(Budget_Item obj) {
        System.out.println("---Here in the insert method for create budget item method---\n");
        Connection c = postgresConnection.getConnection();
        System.out.println("---Here in the insert_item method for create item method AFTER CONNECTION---\n");
        double amount = Double.parseDouble(obj.getAmount());
        String q1 = "insert into budget_item (budget_id, cat_id, amount) values ('" +
                obj.getBudget_id() + "', '" +
                obj.getCat_id() + "', '$" +
                amount + "') RETURNING item_id;";
        System.out.println(q1);
        try {
            Statement stmt = c.createStatement();
            System.out.println("---After create statement in insert item---\n");
            // Inserting data in database

            ResultSet x = stmt.executeQuery(q1);
            System.out.println("************After execute update in insert for insert_item---\n");
            x.next();
            int x_ = x.getInt(1);
            System.out.println(x_);
            if (x_ <= 0) {
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                return x_;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public int insert_cat(Category cat) {
        System.out.println("---Here in the insert method for create cat method---\n");
        Connection c = postgresConnection.getConnection();
        System.out.println("---Here in the insert_cat method for create cat method AFTER CONNECTION---\n");
        String q1 = "insert into category (user_id, cat_name) values ('" +
                cat.getUserId() + "', '" +
                cat.getName() + "') RETURNING cat_id;";
        System.out.println(q1);
        try {
            Statement stmt = c.createStatement();
            System.out.println("---After create statement in insert---\n");
            // Inserting data in database

            ResultSet x = stmt.executeQuery(q1);
            System.out.println("************After execute update in insert for insert_cat---\n");
            x.next();
            int result = x.getInt(1);
            System.out.println(result);
            if (x == null) {
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                return result;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public int insert(Budget budget) {
        System.out.println("---Here in the insert method for create budget method---\n");
        Connection c = postgresConnection.getConnection();
        System.out.println("---Here in the insert method for create budget method AFTER CONNECTION---\n");
        //int user = Integer.parseInt(budget.getUser_id());
        double income = Double.parseDouble(budget.getTotal_income());
        double outcome = Double.parseDouble(budget.getTotal_expense());
        String q1 = "insert into budget (name, user_id, start_date, end_date, total_income, total_expense, description) values('" +
                budget.getName() + "', '" +
                budget.getUser_id() + "', '" +
                budget.getStart_date() + "', '" +
                budget.getEnd_date() + "', '$" +
                income + "', '$" +
                outcome + "', '"  +
                budget.getDescription() + "') RETURNING budget_id;";

                System.out.println(q1);
        try {
            Statement stmt = c.createStatement();
            System.out.println("---After create statement in insert---\n");
            // Inserting data in database

            ResultSet x = stmt.executeQuery(q1);
            x.next();
            int x_ = x.getInt(1);
            System.out.println("---After execute update in insert---\n");
            if (x_ <= 0) {
                throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            else {
                return x_;
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
