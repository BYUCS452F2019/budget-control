package com.budgetControlGroup.budgetControl.Models;

public class TransactionResult {
    private String message;

    public TransactionResult(){}

    public TransactionResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
