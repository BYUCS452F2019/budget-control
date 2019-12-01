package com.budgetControlGroup.budgetControl.Models;

public class TransactionRequest {
    private int user_id;
    private int budget_id;
    private int cat_id;
    private String amount;
    private String date;
    private String description;

    public TransactionRequest(int user_id, int budget_id, int cat_id, String amount, String date, String description) {
        this.user_id = user_id;
        this.budget_id = budget_id;
        this.cat_id = cat_id;
        this.amount = amount;
        this.date = date;
        this.description = description;
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

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
