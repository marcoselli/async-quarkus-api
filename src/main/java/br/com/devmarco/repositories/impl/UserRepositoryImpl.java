package br.com.devmarco.repositories.impl;

import br.com.devmarco.repositories.UserRepository;
import br.com.devmarco.repositories.dtos.UserDTO;
import br.com.devmarco.repositories.models.UserEntity;
import io.smallrye.mutiny.Uni;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepositoryImpl extends AbstractUserRepository implements UserRepository {

    @Inject
    DynamoDbAsyncClient dynamoDB;

    @Override
    public Uni<UserEntity> create(UserEntity userEntity) {
        return Uni.createFrom().completionStage(() -> dynamoDB.putItem(putRequest(userEntity)))
                .onItem().ignore().andSwitchTo(findByNickname(userEntity.getNickname()));
    }

    @Override
    public Uni<UserEntity> update(UserEntity userEntity) {
        return Uni.createFrom().completionStage(() -> dynamoDB.updateItem(updateRequest(userEntity)))
                .onItem().ignore().andSwitchTo(findByNickname(userEntity.getNickname()));
    }

    @Override
    public Uni<UserEntity> delete(String nickname) {
        return Uni.createFrom().completionStage(() -> dynamoDB.deleteItem(deleteRequest(nickname)))
                .onItem().ignore().andSwitchTo(findByNickname(nickname));
    }

    @Override
    public Uni<List<UserEntity>> findAll() {
        return Uni.createFrom().completionStage(() -> dynamoDB.scan(scanRequest()))
                .map(res -> res.items().stream().map(UserEntity::from).collect(Collectors.toList()));
    }

    @Override
    public Uni<UserEntity> findByNickname(String nickname) {
        return Uni.createFrom().completionStage(() -> dynamoDB.getItem(getRequest(nickname)))
                .onItem().transform(res -> UserEntity.from(res.item()));
    }
}
