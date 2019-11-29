package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;
import com.budgetControlGroup.budgetControl.Models.User;

import java.util.List;
import java.util.Map;

public class UserDao {
    private static final String USERS_TABLE = "Users";
    private static final String USER_ID_ATTRIBUTE = "user_id";
    private static final String FIRST_NAME_ATTRIBUTE = "first_name";
    private static final String LAST_NAME_ATTRIBUTE = "last_name";
    private static final String EMAIL_ATTRIBUTE = "email";
    private static final String USERNAME_ATTRIBUTE = "username";
    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final String LAST_LOGIN_ATTRIBUTE = "last_login";
    private static final String DATE_CREATED_ATTRIBUTE = "date_created";

    private AmazonDynamoDB amazonDynamoDB;
    private Table usersTable;

    public UserDao(){
        amazonDynamoDB = Connection.connect();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        usersTable = dynamoDB.getTable(USERS_TABLE);
    }

    public void addUser(User user) {  //I am ignoring the user_id value on the incoming object and assigning it inside this function
        try {
            int newUserId = getLastUserId() + 1;
            Item item = new Item().withPrimaryKey(USER_ID_ATTRIBUTE, newUserId)
                    .withString(FIRST_NAME_ATTRIBUTE, user.getFirstName())
                    .withString(LAST_NAME_ATTRIBUTE, user.getLastName())
                    .withString(EMAIL_ATTRIBUTE, user.getEmail())
                    .withString(USERNAME_ATTRIBUTE, user.getUsername())
                    .withString(PASSWORD_ATTRIBUTE, user.getPassword())
                    .withString(LAST_LOGIN_ATTRIBUTE, user.getLastLogin().toString())
                    .withString(DATE_CREATED_ATTRIBUTE, user.getDateCreated().toString());
            usersTable.putItem(item);
        }catch(Exception e){
            System.out.println("Error adding user: \n" + e.getMessage());
        }
    }

    private int getLastUserId(){
        ScanRequest scanRequest = new ScanRequest().withTableName(USERS_TABLE)
                .withAttributesToGet(USER_ID_ATTRIBUTE);

        ScanResult scanResult = amazonDynamoDB.scan(scanRequest);
        List<Map<String, AttributeValue>> items = scanResult.getItems();
        int highestId = 0;
        for(int i = 0; i < items.size(); i++){
            int userId = Integer.parseInt(items.get(i).get(USER_ID_ATTRIBUTE).getN());
            if(userId > highestId){
                highestId = userId;
            }
        }
        return highestId;
    }
}
