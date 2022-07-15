package br.com.devmarco.services.impl;


import br.com.devmarco.repositories.User;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractUserServiceImpl  {

    public final static String USER_NAME_COL = "userName";
    public final static String USER_PASSWORD_COL = "userPassword";

    public String getTableName() {
        return "TB_USER";
    }

    protected ScanRequest scanRequest() {
        return new ScanRequest().withTableName(getTableName())
                .withAttributesToGet(USER_NAME_COL, USER_PASSWORD_COL);
    }

    protected PutItemRequest putRequest(User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(USER_NAME_COL, new AttributeValue().withS(user.getName()));
        item.put(USER_PASSWORD_COL, new AttributeValue().withS(user.getPassword()));

        return new PutItemRequest()
                .withTableName(getTableName())
                .withItem(item);
    }

    protected GetItemRequest getRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(USER_NAME_COL, new AttributeValue().withS(name));

        return new GetItemRequest()
                .withTableName(getTableName())
                .withKey(key)
                .withAttributesToGet(USER_NAME_COL, USER_PASSWORD_COL);

    }
}
