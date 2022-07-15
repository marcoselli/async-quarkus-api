package br.com.devmarco.services.impl;

import br.com.devmarco.repositories.User;
import br.com.devmarco.repositories.dtos.UserDTO;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl extends AbstractUserServiceImpl{

    @Inject
    AmazonDynamoDBAsyncClient dynamoDB;

    public Uni<List<UserDTO>> findAll() {
        return Uni.createFrom().completionStage(() -> dynamoDB.scan(scanRequest()))
                .onItem().transform(res -> res.items().stream().map(User::from).collect(Collectors.toList()));
    }

    public Uni<List<UserDTO>> create(User user) {

        return Uni.createFrom().completionStage(() -> dynamoDB.putItem(putRequest(user)))
                .onItem().ignore().andSwitchTo(this::findAll);
    }

    public Uni<UserDTO> find(Long userId) {
        return Uni.createFrom().completionStage(() -> dynamoDB.getItem(getRequest(name)))
                .onItem().transform(resp -> UserDTO.from(resp.item()));
    }
}
