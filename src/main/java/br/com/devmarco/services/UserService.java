package br.com.devmarco.services;

import br.com.devmarco.repositories.dtos.UserDTO;

import io.smallrye.mutiny.Uni;
import java.util.List;

public interface UserService  {

    Uni<UserDTO> create(UserDTO userDTO);
    Uni<UserDTO> update(UserDTO userDTO);
    Uni<UserDTO> delete(String nickname);
    Uni<List<UserDTO>> findAll();
    Uni<UserDTO> findByNickname(String nickname);


}
