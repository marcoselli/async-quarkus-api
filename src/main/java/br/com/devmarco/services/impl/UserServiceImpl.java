package br.com.devmarco.services.impl;

import br.com.devmarco.adapters.UserAdapter;
import br.com.devmarco.configs.AwsConfig;
import br.com.devmarco.repositories.User;
import br.com.devmarco.repositories.dtos.UserDTO;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl extends AbstractUserServiceImpl{
    AmazonDynamoDBAsync dynamoDB;

    @Inject
    AwsConfig awsConfig;


    public UserServiceImpl(){
        this.dynamoDB = AmazonDynamoDBAsyncClientBuilder.standard()
                .withRegion(awsConfig.getDefaultRegion())
                .build();
    }
    public Uni<List<UserDTO>> findAll() {
        return Uni.createFrom().item(() -> dynamoDB.scan(scanRequest()))
                .map(res -> res.getItems().stream().map(User::from).collect(Collectors.toList()))
                .map(list -> UserAdapter.convertListToDTO(list));
    }

    public Uni<List<UserDTO>> create(User user) {
        /*
        return Uni.createFrom().completionStage(() -> dynamoDB.putItem(putRequest(user)))
                .onItem().ignore().andSwitchTo(this::findAll);

         */
        return null;
    }

    public Uni<UserDTO> find(Long userId) {
        /*
        return Uni.createFrom().completionStage(() -> dynamoDB.getItem(getRequest(name)))
                .onItem().transform(resp -> UserDTO.from(resp.item()));

         */
        return  null;
    }
}
