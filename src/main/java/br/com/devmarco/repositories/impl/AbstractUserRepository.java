package br.com.devmarco.repositories.impl;


import br.com.devmarco.repositories.models.UserEntity;
import software.amazon.awssdk.services.dynamodb.model.AttributeAction;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.AttributeValueUpdate;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractUserRepository {

    public final static String USER_ID_COL = "userId";
    public final static String USER_NAME_COL = "userName";
    public final static String USER_PASSWORD_COL = "userPassword";
    public final static String USER_CREATED_COL = "userCreatedAt";
    public final static String USER_UPDATED_COL = "userUpdatedAt";
    public final static String USER_STATUS_COL = "userStatus";


    public String getTableName() {
        return "TB_USER";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder()
                .tableName(getTableName())
                .attributesToGet(USER_ID_COL, USER_NAME_COL, USER_PASSWORD_COL, USER_CREATED_COL, USER_UPDATED_COL, USER_STATUS_COL)
                .build();
    }

    protected PutItemRequest putRequest(UserEntity userEntity) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(USER_ID_COL, AttributeValue.builder().s(userEntity.getId()).build());
        item.put(USER_NAME_COL, AttributeValue.builder().s(userEntity.getNickname()).build());
        item.put(USER_PASSWORD_COL, AttributeValue.builder().s(userEntity.getPassword()).build());
        item.put(USER_CREATED_COL, AttributeValue.builder().s(userEntity.getCreatedAt().toString()).build());
        item.put(USER_UPDATED_COL, AttributeValue.builder().s(userEntity.getUpdatedAt().toString()).build());
        item.put(USER_STATUS_COL, AttributeValue.builder().s(userEntity.getStatus().getValue()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected UpdateItemRequest updateRequest(UserEntity userEntity){
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(USER_NAME_COL, AttributeValue.builder().s(userEntity.getNickname()).build());

        Map<String, AttributeValueUpdate> item = new HashMap<>();
        item.put(USER_ID_COL, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(userEntity.getId()).build())
                .action(AttributeAction.PUT)
                .build());

        item.put(USER_PASSWORD_COL, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(userEntity.getPassword()).build())
                .action(AttributeAction.PUT)
                .build());

        item.put(USER_CREATED_COL, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(userEntity.getCreatedAt().toString()).build())
                .action(AttributeAction.PUT)
                .build());

        item.put(USER_UPDATED_COL, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(userEntity.getUpdatedAt().toString()).build())
                .action(AttributeAction.PUT)
                .build());

        item.put(USER_STATUS_COL, AttributeValueUpdate.builder()
                .value(AttributeValue.builder().s(userEntity.getStatus().getValue()).build())
                .action(AttributeAction.PUT)
                .build());


        return UpdateItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributeUpdates(item)
                .build();
    }

    protected DeleteItemRequest deleteRequest(String nickname){
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(USER_NAME_COL, AttributeValue.builder().s(nickname).build());

        return DeleteItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .build();
    }

    protected GetItemRequest getRequest(String nickname) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(USER_NAME_COL, AttributeValue.builder().s(nickname).build());


        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(USER_ID_COL, USER_NAME_COL, USER_PASSWORD_COL, USER_CREATED_COL, USER_UPDATED_COL, USER_STATUS_COL)
                .build();

    }
}
