package com.budgetControlGroup.budgetControl.Models;

public class Category {
    private String name;
    private int user_id;
    private int cat_id;

    public Category() {
    }
    public Category(String name_in, int user_in, int id_in) {
        name = name_in;
        user_id = user_in;
        cat_id = id_in;
    }
    public Category(String name_in, int user_in) {
        name = name_in;
        user_id = user_in;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_in) {
        this.user_id = user_in;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
}
