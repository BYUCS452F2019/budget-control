package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

public class Main {
    public static void main(String[] args){
        ViewTableContents viewTableContents = new ViewTableContents();
        viewTableContents.viewTable("Budget");
    }
}
