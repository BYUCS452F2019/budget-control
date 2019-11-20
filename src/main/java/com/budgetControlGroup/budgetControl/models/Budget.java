package com.budgetControlGroup.budgetControl.Models;

public class Budget {
    private int budget_id;
    private String name;
    private int user_id;
    // Should these be Strings?
    private String start_date;
    private String end_date;
    private String total_income;
    private String total_expense;
    private String description;

    public Budget(int budget_id, String name, int user_id, String start_date,
                  String end_date, String total_income, String total_expense, String description) {
        System.out.println("---Here in the Models.Budget NOT default constructor\n");
        this.budget_id = budget_id;
        this.name = name;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.total_income = total_income;
        this.total_expense = total_expense;
        this.description = description;
    }
    public Budget() {
        System.out.println("---Here in the Models.Budget default constructor\n");
    }

    public int getBudget_id() {
        return budget_id;
    }

    public void setBudget_id(int budget_id) {
        this.budget_id = budget_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTotal_income() {
        return total_income;
    }

    public void setTotal_income(String total_income) {
        this.total_income = total_income;
    }

    public String getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(String total_expense) {
        this.total_expense = total_expense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
