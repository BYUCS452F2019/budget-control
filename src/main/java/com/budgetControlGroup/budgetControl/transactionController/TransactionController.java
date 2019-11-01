package com.budgetControlGroup.budgetControl.transactionController;

import com.budgetControlGroup.budgetControl.models.Transaction;
import com.budgetControlGroup.budgetControl.dataAccess.TransactionDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
