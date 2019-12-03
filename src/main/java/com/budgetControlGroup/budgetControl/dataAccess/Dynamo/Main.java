package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.budgetControlGroup.budgetControl.Models.*;

import java.util.List;

public class Main {
    public static void main(String[] args){
        CategoryDao categoryDao = new CategoryDao();
        try {
            List<Category> categories = categoryDao.getCategoriesForUser(1);
            System.out.println(categories.size());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
//        try {
//            CreateDummyData createDummyData = new CreateDummyData();
//            createDummyData.addTransactionsFromFile();
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
    }
}
