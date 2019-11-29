package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.budgetControlGroup.budgetControl.Models.Budget;

import java.util.List;
import java.util.Map;

public class BudgetDao {
    private static final String BUDGET_TABLE = "Budget";
    private static final String USER_ID_ATTRIBUTE = "user_id";
    private static final String BUDGET_ID_ATTRIBUTE = "budget_id";
    private static final String BUDGET_NAME_ATTRIBUTE = "budget_name";
    private static final String START_DATE_ATTRIBUTE = "start_date";
    private static final String END_DATE_ATTRIBUTE = "end_date";
    private static final String TOTAL_INCOME_ATTRIBUTE = "total_income";
    private static final String TOTAL_EXPENSE_ATTRIBUTE = "total_expense";
    private static final String DESCRIPTION_ATTRIBUTE = "description";

    private AmazonDynamoDB amazonDynamoDB;
    private Table budgetTable;

    public BudgetDao(){
        amazonDynamoDB = Connection.connect();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        budgetTable = dynamoDB.getTable(BUDGET_TABLE);
    }

    public void addBudget(Budget budget) {  //I am ignoring the budget.budgetId value on the incoming object and assigning it inside this function
        try {
            int newBudgetId = getLastBudgetId(budget.getUser_id()) + 1;
            Item item = new Item().withPrimaryKey(USER_ID_ATTRIBUTE, budget.getUser_id(), BUDGET_ID_ATTRIBUTE, newBudgetId)
                    .withString(BUDGET_NAME_ATTRIBUTE, budget.getName())
                    .withString(START_DATE_ATTRIBUTE, budget.getStart_date())
                    .withString(END_DATE_ATTRIBUTE, budget.getEnd_date())
                    .withString(TOTAL_INCOME_ATTRIBUTE, budget.getTotal_income())
                    .withString(TOTAL_EXPENSE_ATTRIBUTE, budget.getTotal_expense())
                    .withString(DESCRIPTION_ATTRIBUTE, budget.getDescription());
            budgetTable.putItem(item);
        }catch(Exception e){
            System.out.println("Error adding budget: \n" + e.getMessage());
        }
    }

    private int getLastBudgetId(int userId){
        QueryRequest queryRequest = new QueryRequest().withTableName(BUDGET_TABLE)
                .withKeyConditionExpression("#user_id = :user_id")
                .addExpressionAttributeNamesEntry("#user_id", USER_ID_ATTRIBUTE)
                .addExpressionAttributeValuesEntry(":user_id", new AttributeValue().withN(String.valueOf(userId)))
                .withLimit(1)
                .withScanIndexForward(false);  //This will give the most recent budgetId

        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if(!items.isEmpty()){
            return Integer.parseInt(items.get(0).get(BUDGET_ID_ATTRIBUTE).getN());
        }else{
            return 0;
        }
    }

}
