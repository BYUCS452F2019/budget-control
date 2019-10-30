package com.budgetControlGroup.budgetControl.dataAccess;

import com.budgetControlGroup.budgetControl.Models.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//This is just a class to test the TransactionDao functionality
public class TransactionScript {


    public static void main(String[] args){
        TransactionDAO transactionDAO = new TransactionDAO();
        try {
            List<Transaction> transactions = transactionDAO.getTransactionsForUser(1);
            System.out.println(transactions.size());
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
