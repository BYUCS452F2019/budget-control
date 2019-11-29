package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.budgetControlGroup.budgetControl.Models.Transaction;
import com.budgetControlGroup.budgetControl.Models.TransactionRequest;
import com.budgetControlGroup.budgetControl.Models.TransactionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionDao {
    private static final String TRANSACTION_TABLE = "Transaction";
    private static final String BUDGET_ID_ATTRIBUTE = "budget_id";
    private static final String BUDGET_NAME_ATTRIBUTE = "budget_name";
    private static final String TRANSACTION_ID_ATTRIBUTE = "transaction_id";
    private static final String CATEGORY_ID_ATTRIBUTE = "category_id";
    private static final String CATEGORY_NAME_ATTRIBUTE = "category_name";
    private static final String AMOUNT_ATTRIBUTE = "amount";
    private static final String DATE_ATTRIBUTE = "date";
    private static final String DESCRIPTION_ATTRIBUTE = "description";

    AmazonDynamoDB amazonDynamoDB;
    Table transactionTable;

    public TransactionDao(){
        amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        transactionTable = dynamoDB.getTable(TRANSACTION_TABLE);
    }

    public List<Transaction> getTransactionsForBudget(int budgetId) throws Exception {
        ArrayList<Transaction> transactions = new ArrayList<>();

        QueryRequest queryRequest = new QueryRequest().withTableName(TRANSACTION_TABLE)
                .withKeyConditionExpression("#budget_id = :budget_id")
                .addExpressionAttributeNamesEntry("#budget_id", BUDGET_ID_ATTRIBUTE)
                .addExpressionAttributeValuesEntry(":budget_id", new AttributeValue().withN(String.valueOf(budgetId)))
                .withScanIndexForward(false);  //This will cause the most recent transaction items to be listed first.

        QueryResult queryResult = amazonDynamoDB.query(queryRequest);

        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if(items != null){
            for(Map<String, AttributeValue> item : items){
                transactions.add(mapToTransaction(item));
            }
        }
        return transactions;
    }

    private Transaction mapToTransaction(Map<String, AttributeValue> map) throws Exception{
        try{
            int transactionId = Integer.parseInt(map.get(TRANSACTION_ID_ATTRIBUTE).getN());
            String budgetName = map.get(BUDGET_NAME_ATTRIBUTE).getS();
            String categoryName = map.get(CATEGORY_NAME_ATTRIBUTE).getS();
            String amount = map.get(AMOUNT_ATTRIBUTE).getS();
            String date = map.get(DATE_ATTRIBUTE).getS();
            String description = map.get(DESCRIPTION_ATTRIBUTE).getS();
            return new Transaction(transactionId, budgetName, categoryName, amount, date, description);
        }catch(Exception e){
            throw new Exception("Error converting from map to transaction!");
        }
    }

    public TransactionResult addTransaction(TransactionRequest transactionRequest) {
        try {
            int newTransId = getLastTransactionId(transactionRequest.getBudget_id()) + 1;
            Item item = new Item().withPrimaryKey(BUDGET_ID_ATTRIBUTE, transactionRequest.getBudget_id(), TRANSACTION_ID_ATTRIBUTE, newTransId)
                    .withString(BUDGET_NAME_ATTRIBUTE, "SomeBudget")
                    .withNumber(CATEGORY_ID_ATTRIBUTE, transactionRequest.getCat_id())
                    .withString(CATEGORY_NAME_ATTRIBUTE, "SomeCategory")
                    .withString(AMOUNT_ATTRIBUTE, transactionRequest.getAmount())
                    .withString(DATE_ATTRIBUTE, transactionRequest.getDate())
                    .withString(DESCRIPTION_ATTRIBUTE, transactionRequest.getDescription());
            transactionTable.putItem(item);
            return new TransactionResult("Success adding transaction!");
        }catch(Exception e){
            return new TransactionResult("Error adding transaction: \n" + e.getMessage());
        }
    }

    private int getLastTransactionId(int budgetId){
        QueryRequest queryRequest = new QueryRequest().withTableName(TRANSACTION_TABLE)
                .withKeyConditionExpression("#budget_id = :budget_id")
                .addExpressionAttributeNamesEntry("#budget_id", BUDGET_ID_ATTRIBUTE)
                .addExpressionAttributeValuesEntry(":budget_id", new AttributeValue().withN(String.valueOf(budgetId)))
                .withLimit(1)
                .withScanIndexForward(false);  //This will cause the most recent transaction items to be listed first.

        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if(!items.isEmpty()){
            return Integer.parseInt(items.get(0).get(TRANSACTION_ID_ATTRIBUTE).getN());
        }else{
            return 0;
        }
    }
}
