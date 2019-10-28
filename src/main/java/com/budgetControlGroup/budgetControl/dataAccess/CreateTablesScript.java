package com.budgetControlGroup.budgetControl.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTablesScript {
    private final String url = "jdbc:postgresql://localhost/budgetcontrol";
    private final String user = "postgres";
    private final String password = "jmcBudget452";

    /* --- START OF TABLE CREATION STRINGS --- */
    private final String table_drop = "DROP TABLE IF EXISTS transaction, budget_item, category, budget, users CASCADE;";
    private final String create_user = "CREATE TABLE IF NOT EXISTS users (" +
            "user_id SERIAL PRIMARY KEY, " +
            "first_name VARCHAR(25), " +
            "last_name VARCHAR(25), " +
            "email VARCHAR(50), " +
            "username VARCHAR(15), " +
            "password VARCHAR(100), " +
            "last_login DATE, " +
            "date_created DATE" +
            ");";

    private final String create_budget = "CREATE TABLE IF NOT EXISTS budget (" +
            "budget_id SERIAL PRIMARY KEY, " +
            "name VARCHAR(25), " +
            "user_id INT REFERENCES users(user_id), " +
            "start_date DATE, " +
            "end_date DATE, " +
            "total_income MONEY, " +
            "total_expense MONEY, " +
            "description VARCHAR(200)" +
            ");";


    private final String create_budget_item = "CREATE TABLE IF NOT EXISTS budget_item (" +
            "item_id SERIAL PRIMARY KEY, " +
            "budget_id INT REFERENCES budget(budget_id), " +
            "cat_id INT REFERENCES category(cat_id), " +
            "amount MONEY" +
            ");";

    private final String create_category = "CREATE TABLE IF NOT EXISTS category (" +
            "cat_id SERIAL PRIMARY KEY, " +
            "user_id INT REFERENCES users(user_id), " +
            "cat_name VARCHAR(50)" +
            ");";


    private final String create_transaction = "CREATE TABLE IF NOT EXISTS transaction (" +
            "trans_id SERIAL PRIMARY KEY, " +
            "budget_id INT REFERENCES budget(budget_id), " +
            "cat_id INT REFERENCES category(cat_id), " +
            "amount MONEY, " +
            "date DATE, " +
            "description VARCHAR(200)" +
            ");";

    /* --- END OF TABLE CREATION STRING --- */

    public Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!");
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void createTables(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(table_drop);
            stmt.executeUpdate(create_user);
            stmt.executeUpdate(create_budget);
            stmt.executeUpdate(create_category);
            stmt.executeUpdate(create_budget_item);
            stmt.executeUpdate(create_transaction);
            stmt.close();
            conn.close();
            System.out.println("created user table gooders\n");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args){
        CreateTablesScript createTablesScript = new CreateTablesScript();
        Connection conn = createTablesScript.connect(); 		// returns connection object
        createTablesScript.createTables(conn);
    }
}
