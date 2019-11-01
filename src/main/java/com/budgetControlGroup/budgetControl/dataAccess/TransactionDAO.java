package com.budgetControlGroup.budgetControl.dataAccess;

import com.budgetControlGroup.budgetControl.Models.Transaction;
import com.budgetControlGroup.budgetControl.Models.TransactionRequest;
import com.budgetControlGroup.budgetControl.Models.TransactionResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private final String SELECT_TRANSACTIONS = "SELECT * FROM transaction";
    private final String SELECT_TRANSACTIONS_FOR_USER = "SELECT t.trans_id, b.name, c.cat_name, t.amount, t.date, t.description FROM transaction t JOIN budget b ON t.budget_id = b.budget_id JOIN category c ON t.cat_id = c.cat_id WHERE b.user_id = ?";
    private final String SELECT_TRANSACTIONS_FOR_BUDGET = "SELECT * FROM transaction WHERE budget_id = ?"; //Use a prepared statement
    private final String SELECT_TRANSACTIONS_FOR_CATEGORY = "SELECT * FROM transaction WHERE cat_id = ?";

    private final String CREATE_TRANSACTION = "INSERT INTO transaction (budget_id, cat_id, amount, date, description) VALUES (";

    private List<Transaction> retrieveTransactionsNoParams(String selectStatement) throws SQLException {
        Connection conn = DBConnection.connect();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(selectStatement);
        return convertToTransactions(statement, conn, rs);
    }

    private List<Transaction> retrieveTransactionsOneParam(String selectStatement, int param) throws SQLException {
        Connection conn = DBConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(selectStatement);
        pstmt.setInt(1, param);
        ResultSet rs = pstmt.executeQuery();
        return convertToTransactions(pstmt, conn, rs);
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        return retrieveTransactionsNoParams(SELECT_TRANSACTIONS);
    }

    public List<Transaction> getTransactionsForUser(int userId) throws SQLException {
        return retrieveTransactionsOneParam(SELECT_TRANSACTIONS_FOR_USER, userId);
    }

    public List<Transaction> getTransactionsForBudget(int budgetId) throws SQLException {
        return retrieveTransactionsOneParam(SELECT_TRANSACTIONS_FOR_BUDGET, budgetId);
    }

    public List<Transaction> getTransactionsForCategory(int catId) throws SQLException {
        return retrieveTransactionsOneParam(SELECT_TRANSACTIONS_FOR_CATEGORY, catId);
    }

    public TransactionResult addTransaction(TransactionRequest transactionRequest) throws SQLException {
        Connection conn = DBConnection.connect();
        Statement stmt = conn.createStatement();
        StringBuilder createStatement = new StringBuilder(CREATE_TRANSACTION);
        createStatement.append("'").append(transactionRequest.getBudget_id()).append("', '");
        createStatement.append(transactionRequest.getCat_id()).append("', '");
        createStatement.append(transactionRequest.getAmount()).append("', '");
        createStatement.append(transactionRequest.getDate()).append("', '");
        createStatement.append(transactionRequest.getDescription()).append("')");
        System.out.println(createStatement.toString());
        stmt.executeUpdate(createStatement.toString());  //Eventually should see if pgres passes back a value
        return new TransactionResult("Successful!");
    }

    private TransactionResult convertToTransactionResult(Statement statement, Connection connection, ResultSet rs) throws SQLException {
        TransactionResult transactionResult = new TransactionResult();
        try{
            rs.next();
            System.out.println(rs.getString(1));
        } catch(SQLException e){
            statement.close();
            connection.close();
            throw new SQLException(e.getMessage());
        }
        return transactionResult;
    }

    private List<Transaction> convertToTransactions(Statement statement, Connection connection, ResultSet rs) throws SQLException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransaction_id(rs.getInt("trans_id"));
                transaction.setBudget_name(rs.getString("name"));
                transaction.setCat_name(rs.getString("cat_name"));
                transaction.setAmount(rs.getString("amount"));
                transaction.setDate(rs.getString("date"));
                transaction.setDescription(rs.getString("description"));
                transactions.add(transaction);
            }
            statement.close();
            connection.close();
            return transactions;
        } catch (SQLException e){
            statement.close();
            connection.close();
            throw new SQLException("Error converting result set to transactions!");
        }
    }
}
