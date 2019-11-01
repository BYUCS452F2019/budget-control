package com.budgetControlGroup.budgetControl.transactionController;

import com.budgetControlGroup.budgetControl.Models.Transaction;
import com.budgetControlGroup.budgetControl.Models.TransactionRequest;
import com.budgetControlGroup.budgetControl.Models.TransactionResult;
import com.budgetControlGroup.budgetControl.dataAccess.TransactionDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.WebMethod;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    @RequestMapping("")
    public List<Transaction> getTransactions(int userId){
        //TODO: eventually get the logged in user id
        TransactionDAO dao = new TransactionDAO();
        try {
            return dao.getTransactionsForUser(userId);
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return new ArrayList<Transaction>();
        }
    }

    @RequestMapping("/id")
    public List<Transaction> getTransactions(@RequestParam(value="user_id", defaultValue="1") String userId){
        TransactionDAO dao = new TransactionDAO();
        try {
            return dao.getTransactionsForUser(Integer.parseInt(userId));
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return new ArrayList<Transaction>();
        }
    }

    @RequestMapping("/add")
    public TransactionResult addTransaction(@RequestParam(value="budget_id") String budgetId, @RequestParam(value="cat_id") String catId, @RequestParam(value="amount") String amount, @RequestParam(value="date") String date, @RequestParam(value="description") String description){
        TransactionRequest incomingTransaction = new TransactionRequest(Integer.parseInt(budgetId), Integer.parseInt(catId), amount, date, description);
        TransactionDAO dao = new TransactionDAO();
        try {
            return dao.addTransaction(incomingTransaction);
        } catch(SQLException e){
            System.err.println(e.getMessage());
            return new TransactionResult(e.getMessage());
        }
    }
}
