package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.budgetControlGroup.budgetControl.Models.Budget;

import java.util.ArrayList;
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
            int newBudgetId = getLastBudgetId() + 1;
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

    public List<Budget> getBudgetsForUser(int userId) throws Exception {
        ArrayList<Budget> budgets = new ArrayList<>();

        QueryRequest queryRequest = new QueryRequest().withTableName(BUDGET_TABLE)
                .withKeyConditionExpression("#user_id = :user_id")
                .addExpressionAttributeNamesEntry("#user_id", USER_ID_ATTRIBUTE)
                .addExpressionAttributeValuesEntry(":user_id", new AttributeValue().withN(String.valueOf(userId)));

        QueryResult queryResult = amazonDynamoDB.query(queryRequest);

        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if(items != null){
            for(Map<String, AttributeValue> item : items){
                budgets.add(mapToBudget(item));
            }
        }
        return budgets;
    }

    private Budget mapToBudget(Map<String, AttributeValue> map) throws Exception{
        try{
            int userId = Integer.parseInt(map.get(USER_ID_ATTRIBUTE).getN());
            int budgetId = Integer.parseInt(map.get(BUDGET_ID_ATTRIBUTE).getN());
            String budgetName = map.get(BUDGET_NAME_ATTRIBUTE).getS();
            String startDate = map.get(START_DATE_ATTRIBUTE).getS();
            String endDate = map.get(END_DATE_ATTRIBUTE).getS();
            String totalIncome = map.get(TOTAL_INCOME_ATTRIBUTE).getS();
            String totalExpense = map.get(TOTAL_EXPENSE_ATTRIBUTE).getS();
            String description = map.get(DESCRIPTION_ATTRIBUTE).getS();
            return new Budget(budgetId, budgetName, userId, startDate, endDate, totalIncome, totalExpense, description);
        }catch(Exception e){
            throw new Exception("Error converting from map to budget!");
        }
    }

    private int getLastBudgetId(){
        ScanRequest scanRequest = new ScanRequest().withTableName(BUDGET_TABLE)
                .withAttributesToGet(BUDGET_ID_ATTRIBUTE);

        ScanResult scanResult = amazonDynamoDB.scan(scanRequest);
        List<Map<String, AttributeValue>> items = scanResult.getItems();
        int highestId = 0;
        for(int i = 0; i < items.size(); i++){
            int budgetId = Integer.parseInt(items.get(i).get(BUDGET_ID_ATTRIBUTE).getN());
            if(budgetId > highestId){
                highestId = budgetId;
            }
        }
        return highestId;
    }

}
