package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.model.*;
import com.budgetControlGroup.budgetControl.Models.User;

import java.util.Date;
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

    public UserDao() {
        amazonDynamoDB = Connection.connect();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        usersTable = dynamoDB.getTable(USERS_TABLE);
    }

    public User addUser(User user) {  //I am ignoring the user_id value on the incoming object and assigning it inside this function
        try {
            if(exists(user)) {
                return null;
            }
            int userid = getLastUserId() + 1;
            Item item = new Item().withPrimaryKey(USER_ID_ATTRIBUTE, userid)
                    .withString(FIRST_NAME_ATTRIBUTE, user.getFirstName())
                    .withString(LAST_NAME_ATTRIBUTE, user.getLastName())
                    .withString(EMAIL_ATTRIBUTE, user.getEmail())
                    .withString(USERNAME_ATTRIBUTE, user.getUsername())
                    .withString(PASSWORD_ATTRIBUTE, user.getPassword())
                    .withString(LAST_LOGIN_ATTRIBUTE, new Date().toString())
                    .withString(DATE_CREATED_ATTRIBUTE, new Date().toString());
            usersTable.putItem(item);
            user.setUserId(userid);
        } catch(Exception e) {
            System.out.println("Error adding user: \n" + e.getMessage());
            return null;
        }
        return user;

    }

    public User login(User user) {
        try {
            ScanResult scanResult = getScan(user);
            List<Map<String, AttributeValue>> items = scanResult.getItems();
            for (Map<String, AttributeValue> item : items) {
                if(item.get(USERNAME_ATTRIBUTE).getS().equals(user.getUsername()) &&
                    item.get(PASSWORD_ATTRIBUTE).getS().equals(user.getPassword())) {
                    return mapToUser(item);
                }
            }
        }catch(Exception e){
            System.out.println("Error logging in: \n" + e.getMessage());
        }
        return null;
    }

    private ScanResult getScan(User user) {
        ScanRequest scanRequest = new ScanRequest().withTableName(USERS_TABLE)
            .withAttributesToGet(USER_ID_ATTRIBUTE,
                FIRST_NAME_ATTRIBUTE,
                LAST_NAME_ATTRIBUTE,
                EMAIL_ATTRIBUTE,
                USERNAME_ATTRIBUTE,
                PASSWORD_ATTRIBUTE,
                LAST_LOGIN_ATTRIBUTE,
                DATE_CREATED_ATTRIBUTE);
        return amazonDynamoDB.scan(scanRequest);

    }

    private boolean exists(User user) {
        ScanResult scanResult = getScan(user);
        List<Map<String, AttributeValue>> items = scanResult.getItems();
        for (Map<String, AttributeValue> item : items) {
            if(item.get(USERNAME_ATTRIBUTE).getS().equals(user.getUsername()) &&
                item.get(PASSWORD_ATTRIBUTE).getS().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private User mapToUser(Map<String, AttributeValue> item) {
        return new User(Integer.parseInt(item.get(USER_ID_ATTRIBUTE).getN()),
                        item.get(USERNAME_ATTRIBUTE).getS(),
                        item.get(FIRST_NAME_ATTRIBUTE).getS(),
                        item.get(LAST_NAME_ATTRIBUTE).getS(),
                        item.get(EMAIL_ATTRIBUTE).getS(),
                        new Date(item.get(LAST_LOGIN_ATTRIBUTE).getS()),
                        new Date(item.get(DATE_CREATED_ATTRIBUTE).getS()),
                        null
                        );
        /*THIS DOES NOT GET DATES CURRENTLY*/
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
