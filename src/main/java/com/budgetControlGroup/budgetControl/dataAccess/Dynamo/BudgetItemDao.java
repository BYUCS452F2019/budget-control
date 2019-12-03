package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.budgetControlGroup.budgetControl.Models.Budget_Item;

import java.util.List;
import java.util.Map;

public class BudgetItemDao {
    private static final String BUDGET_ITEM_TABLE = "BudgetItem";
    private static final String BUDGET_ID_ATTRIBUTE = "budget_id";
    private static final String BUDGET_ITEM_ID_ATTRIBUTE = "budget_item_id";
    private static final String CATEGORY_ID_ATTRIBUTE = "category_id";
    private static final String AMOUNT_ATTRIBUTE = "amount";

    private AmazonDynamoDB amazonDynamoDB;
    private Table budgetItemTable;

    public BudgetItemDao(){
        amazonDynamoDB = Connection.connect();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        budgetItemTable = dynamoDB.getTable(BUDGET_ITEM_TABLE);
    }

    public void addBudgetItem(Budget_Item budgetItem) {  //I am ignoring the item_id value on the incoming object and assigning it inside this function
        try {
            int newBudgetItemId = getLastBudgetItemId(budgetItem.getBudget_id()) + 1;
            Item item = new Item().withPrimaryKey(BUDGET_ID_ATTRIBUTE, budgetItem.getBudget_id(), BUDGET_ITEM_ID_ATTRIBUTE, newBudgetItemId)
                    .withNumber(CATEGORY_ID_ATTRIBUTE, budgetItem.getCat_id())
                    .withString(AMOUNT_ATTRIBUTE, budgetItem.getAmount());
            budgetItemTable.putItem(item);
        }catch(Exception e){
            System.out.println("Error adding budget item: \n" + e.getMessage());
        }
    }

    private int getLastBudgetItemId(int budgetId){
        QueryRequest queryRequest = new QueryRequest().withTableName(BUDGET_ITEM_TABLE)
                .withKeyConditionExpression("#budget_id = :budget_id")
                .addExpressionAttributeNamesEntry("#budget_id", BUDGET_ID_ATTRIBUTE)
                .addExpressionAttributeValuesEntry(":budget_id", new AttributeValue().withN(String.valueOf(budgetId)))
                .withLimit(1)
                .withScanIndexForward(false);  //This will give the most recent budgetId

        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if(!items.isEmpty()){
            return Integer.parseInt(items.get(0).get(BUDGET_ITEM_ID_ATTRIBUTE).getN());
        }else{
            return 0;
        }
    }
}
