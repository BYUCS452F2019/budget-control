package com.budgetControlGroup.budgetControl.dataAccess;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateDummyData {
    private final String url = "jdbc:postgresql:budgetcontrol";
    private final String user = "postgres";
    private final String password = "jmcBudget452";

    private final String INSERT_USERS = "INSERT INTO users (first_name, last_name, email, username, \"password\", last_login, date_created) VALUES ('jason', 'cannon', 'cannon@byu.edu', 'cannon', 'hi', '2018-09-08', '2017-09-08');";

    private String buildCreateUserStatement(String firstName, String lastName, String email, String username, String password, String lastLogin, String dateCreated){
        StringBuilder createStatement = new StringBuilder("INSERT INTO users (first_name, last_name, email, username, \"password\", last_login, date_created) VALUES (");
        createStatement.append("'").append(firstName).append("', ");
        createStatement.append("'").append(lastName).append("', ");
        createStatement.append("'").append(email).append("', ");
        createStatement.append("'").append(username).append("', ");
        createStatement.append("'").append(password).append("', ");
        createStatement.append("'").append(lastLogin).append("', ");
        createStatement.append("'").append(dateCreated).append("');");
        return createStatement.toString();
    }

    private String buildCreateStatementFromFile(String initialString, int numParams, String filename) throws Exception {
        StringBuilder createStatement = new StringBuilder(initialString);
        Scanner scanner = new Scanner(new File(filename));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split("___");
            if(tokens.length != numParams){
                throw new Exception("Invalid number of parameters in file row!!!");
            }
            createStatement.append("(");
            for(int i = 0; i < numParams; i++){
                if(i == numParams - 1){
                    createStatement.append("'").append(tokens[i]).append("')");
                }
                else {
                    createStatement.append("'").append(tokens[i]).append("', ");
                }
            }
            if(scanner.hasNextLine()) {
                createStatement.append(",\n\t");
            }
        }
        createStatement.append(";");
        return createStatement.toString();
    }

    private String buildCreateUsersStatementFromFile(String filename) throws Exception {
        String initialString = "INSERT INTO users (first_name, last_name, email, username, \"password\", last_login, date_created) VALUES \n\t";
        return buildCreateStatementFromFile(initialString, 7, filename);
    }

    private String buildCreateBudgetStatementFromFile(String filename) throws Exception {
        String initialString = "INSERT INTO budget (name, user_id, start_date, end_date, total_income, total_expense, description) VALUES \n\t";
        return buildCreateStatementFromFile(initialString, 7, filename);
    }

    private String buildCreateBudgetItemStatementFromFile(String filename) throws Exception {
        String initialString = "INSERT INTO budget_item (budget_id, cat_id, amount) VALUES \n\t";
        return buildCreateStatementFromFile(initialString, 3, filename);
    }

    private String buildCreateTransactionStatementFromFile(String filename) throws Exception {
        String initialString = "INSERT INTO transaction (budget_id, cat_id, amount, date, description) VALUES \n\t";
        return buildCreateStatementFromFile(initialString, 5, filename);
    }

    private String buildCreateCategoryStatementFromFile(String filename) throws Exception {
        String initialString = "INSERT INTO category (user_id, cat_name) VALUES \n\t";
        return buildCreateStatementFromFile(initialString, 2, filename);
    }

    private Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!");
        } catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return conn;
    }
    private void insertData(Connection conn) {
        try {
            String createUsers = buildCreateUsersStatementFromFile("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyUsers.txt");
            String createBudgets = buildCreateBudgetStatementFromFile("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyBudgets.txt");
            String createCategories = buildCreateCategoryStatementFromFile("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyCategories.txt");
            String createBudgetItems = buildCreateBudgetItemStatementFromFile("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyBudgetItems.txt");
            String createTransactions = buildCreateTransactionStatementFromFile("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyTransactions.txt");

            Statement stmt = conn.createStatement();
            //Order matters in the execution of the insert statements below
            stmt.executeUpdate(createUsers);
            stmt.executeUpdate(createCategories);
            stmt.executeUpdate(createBudgets);
            stmt.executeUpdate(createBudgetItems);
            stmt.executeUpdate(createTransactions);

            stmt.close();
            conn.close();
            System.out.println("Inserted Data!!");
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){
        CreateDummyData createDummyData = new CreateDummyData();
        Connection conn = createDummyData.connect(); 		// returns connection object
        createDummyData.insertData(conn);
    }
}
