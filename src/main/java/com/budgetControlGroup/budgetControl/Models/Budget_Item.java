package com.budgetControlGroup.budgetControl.Models;

public class Budget_Item {
    private int item_id;
    private int budget_id;
    private int cat_id;
    private String amount;

    public Budget_Item() {
    }
    public Budget_Item(int budget_in, int cat_in, String amount_in) {
        budget_id = budget_in;
        cat_id = cat_in;
        amount = amount_in;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
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

    @Override
    public String toString() {
        return "Budget_Item{" +
                "item_id=" + item_id +
                ", budget_id=" + budget_id +
                ", cat_id=" + cat_id +
                ", amount='" + amount + '\'' +
                '}';
    }
}
