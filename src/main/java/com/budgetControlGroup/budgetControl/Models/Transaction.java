package com.budgetControlGroup.budgetControl.Models;

public class Transaction {
    private int transaction_id;
    private String budget_name;
    private String cat_name;
    private String amount;
    private String date;
    private String description;

    public Transaction(){}

    public Transaction(int transaction_id, String budget_name, String cat_name, String amount, String date, String description) {
        this.transaction_id = transaction_id;
        this.budget_name = budget_name;
        this.cat_name = cat_name;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getBudget_name() {
        return budget_name;
    }

    public void setBudget_name(String budget_name) {
        this.budget_name = budget_name;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
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
