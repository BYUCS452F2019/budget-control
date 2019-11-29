package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.budgetControlGroup.budgetControl.Models.Category;

import java.util.List;
import java.util.Map;

public class CategoryDao {
    private static final String CATEGORY_TABLE = "Category";
    private static final String USER_ID_ATTRIBUTE = "user_id";
    private static final String CATEGORY_ID_ATTRIBUTE = "category_id";
    private static final String CATEGORY_NAME_ATTRIBUTE = "category_name";

    private AmazonDynamoDB amazonDynamoDB;
    private Table categoryTable;

    public CategoryDao(){
        amazonDynamoDB = Connection.connect();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        categoryTable = dynamoDB.getTable(CATEGORY_TABLE);
    }

    public void addCategoryItem(Category category) {  //I am ignoring the cat_id value on the incoming object and assigning it inside this function
        try {
            int newCategoryId = getLastCategoryId(category.getUserId()) + 1;
            Item item = new Item().withPrimaryKey(USER_ID_ATTRIBUTE, category.getUserId(), CATEGORY_ID_ATTRIBUTE, newCategoryId)
                    .withString(CATEGORY_NAME_ATTRIBUTE, category.getName());
            categoryTable.putItem(item);
        }catch(Exception e){
            System.out.println("Error adding category: \n" + e.getMessage());
        }
    }

    private int getLastCategoryId(int userId){
        QueryRequest queryRequest = new QueryRequest().withTableName(CATEGORY_TABLE)
                .withKeyConditionExpression("#user_id = :user_id")
                .addExpressionAttributeNamesEntry("#user_id", USER_ID_ATTRIBUTE)
                .addExpressionAttributeValuesEntry(":user_id", new AttributeValue().withN(String.valueOf(userId)))
                .withLimit(1)
                .withScanIndexForward(false);  //This will give the most recent budgetId

        QueryResult queryResult = amazonDynamoDB.query(queryRequest);
        List<Map<String, AttributeValue>> items = queryResult.getItems();
        if(!items.isEmpty()){
            return Integer.parseInt(items.get(0).get(CATEGORY_ID_ATTRIBUTE).getN());
        }else{
            return 0;
        }
    }
}
