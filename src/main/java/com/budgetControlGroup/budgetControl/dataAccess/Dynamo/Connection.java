package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class Connection {
    private static AmazonDynamoDB amazonDynamoDB;
    public static AmazonDynamoDB connect(){
        if(amazonDynamoDB == null){
            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion("us-west-2").build();
        }
        return amazonDynamoDB;
    }
}
