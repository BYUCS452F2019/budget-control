package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.budgetControlGroup.budgetControl.Models.Budget;
import com.budgetControlGroup.budgetControl.Models.Budget_Item;
import com.budgetControlGroup.budgetControl.Models.Category;
import com.budgetControlGroup.budgetControl.Models.User;

import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;

public class CreateDummyData {
    private UserDao userDAO;
    private BudgetDao budgetDao;
    private CategoryDao categoryDao;
    private BudgetItemDao budgetItemDao;
    private TransactionDao transactionDAO;

    public CreateDummyData(){
        userDAO = new UserDao();
        budgetDao = new BudgetDao();
        categoryDao = new CategoryDao();
        budgetItemDao = new BudgetItemDao();
        transactionDAO = new TransactionDao();
    }

    public void addUsersFromFile() throws Exception {
        Scanner scanner = new Scanner(new File("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyUsers.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split("___");
            if(tokens.length != 7){
                throw new Exception("Invalid number of parameters in file row!!!");
            }
            User user = new User(0, tokens[3], tokens[0], tokens[1], tokens[2], new Date(), new Date(), tokens[4]);
            userDAO.addUser(user);
        }
    }

    public void addBudgetsFromFile() throws Exception {
        Scanner scanner = new Scanner(new File("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyBudgets.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split("___");
            if(tokens.length != 7){
                throw new Exception("Invalid number of parameters in file row!!!");
            }
            Budget budget = new Budget(0, tokens[0], Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
            budgetDao.addBudget(budget);
        }
    }

    public void addCategoriesFromFile() throws Exception {
        Scanner scanner = new Scanner(new File("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyCategories.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split("___");
            if(tokens.length != 2){
                throw new Exception("Invalid number of parameters in file row!!!");
            }
            Category category = new Category(tokens[1], Integer.parseInt(tokens[0]));
            categoryDao.addCategoryItem(category);
        }
    }

    public void addBudgetItemsFromFile() throws Exception {
        Scanner scanner = new Scanner(new File("src/main/java/com/budgetControlGroup/budgetControl/dataAccess/dummyBudgetItems.txt"));
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] tokens = line.split("___");
            if(tokens.length != 3){
                throw new Exception("Invalid number of parameters in file row!!!");
            }
            Budget_Item budget_item = new Budget_Item(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2]);
            budgetItemDao.addBudgetItem(budget_item);
        }
    }

    private void insertData(Connection conn) {

    }
}
