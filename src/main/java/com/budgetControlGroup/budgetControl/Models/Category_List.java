package com.budgetControlGroup.budgetControl.Models;

import java.util.ArrayList;

public class Category_List {

    private int user_id;
    private int budget_id;
    private ArrayList<String> names;
    private ArrayList<String> expenses;

    public Category_List() {
        System.out.println("---Here in the Models.Category_List default constructor\n");
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public void setExpenses(ArrayList<String> expenses) {
        this.expenses = expenses;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getExpenses() {
        return expenses;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public int getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(int budget_id) {
        this.budget_id = budget_id;
    }

    @Override
    public String toString() {
        return "Category_List{" +
                "user=" + user_id +
                ", budget_id=" + budget_id +
                ", names=" + names +
                ", expenses=" + expenses +
                '}';
    }
}
