package com.budgetControlGroup.budgetControl.dataAccess.Dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ViewTableContents {
    private static final String USERS_TABLE = "Users";

    private AmazonDynamoDB amazonDynamoDB;

    public ViewTableContents(){
        amazonDynamoDB = Connection.connect();
    }

    public void viewTable(String tableName){
        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);

        ScanResult scanResult = amazonDynamoDB.scan(scanRequest);
        List<Map<String, AttributeValue>> items = scanResult.getItems();
        for(int i = 0; i < items.size(); i++){
            Map<String, AttributeValue> map = items.get(i);
            Iterator<Map.Entry<String, AttributeValue>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, AttributeValue> entry = iterator.next();
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("------------------------------------------------");
        }
    }
}
