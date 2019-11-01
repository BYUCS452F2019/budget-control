package com.budgetControlGroup.budgetControl.Models;

public class TransactionRequest {
    private int budget_id;
    private int cat_id;
    private String amount;
    private String date;
    private String description;

    public TransactionRequest(int budget_id, int cat_id, String amount, String date, String description) {
        this.budget_id = budget_id;
        this.cat_id = cat_id;
        this.amount = amount;
        this.date = date;
        this.description = description;
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
