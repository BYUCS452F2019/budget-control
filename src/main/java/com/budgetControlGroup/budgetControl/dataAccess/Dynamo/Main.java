package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.budgetControlGroup.budgetControl.Models.Transaction;
import com.budgetControlGroup.budgetControl.Models.TransactionRequest;
import com.budgetControlGroup.budgetControl.Models.TransactionResult;

import java.util.List;

public class Main {
    public static void main(String[] args){
        TransactionDao transactionDao = new TransactionDao();
        try {
            List<Transaction> transactions = transactionDao.getTransactionsForBudget(1);
            System.out.println(transactions.size());
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
