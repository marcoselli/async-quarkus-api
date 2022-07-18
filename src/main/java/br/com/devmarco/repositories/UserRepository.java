package br.com.devmarco.repositories;

import br.com.devmarco.repositories.dtos.UserDTO;
import br.com.devmarco.repositories.models.UserEntity;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface UserRepository{

    Uni<UserEntity> create(UserEntity userEntity);
    Uni<UserEntity> update(UserEntity userEntity);
    Uni<UserEntity> delete(String nickname);
    Uni<List<UserEntity>> findAll();
    Uni<UserEntity> findByNickname(String nickname);


}
