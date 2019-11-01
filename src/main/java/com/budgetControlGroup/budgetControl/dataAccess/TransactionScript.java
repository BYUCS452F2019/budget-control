package com.budgetControlGroup.budgetControl.dataAccess;

import com.budgetControlGroup.budgetControl.Models.Transaction;
import com.budgetControlGroup.budgetControl.Models.TransactionRequest;
import com.budgetControlGroup.budgetControl.Models.TransactionResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//This is just a class to test the TransactionDao functionality
public class TransactionScript {


    public static void main(String[] args){
        TransactionDAO transactionDAO = new TransactionDAO();
        try {
            //List<Transaction> transactions = transactionDAO.getTransactionsForUser(1);
            //System.out.println(transactions.size());
            TransactionRequest transactionRequest = new TransactionRequest(1, 1, "$57.32", "2019-10-31", "This is the description");
            TransactionResult transactionResult = transactionDAO.addTransaction(transactionRequest);
            System.out.println(transactionResult.getMessage());
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
