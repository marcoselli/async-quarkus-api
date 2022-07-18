package br.com.devmarco.services.impl;

import br.com.devmarco.adapters.UserAdapter;
import br.com.devmarco.repositories.UserRepository;
import br.com.devmarco.repositories.dtos.UserDTO;

import br.com.devmarco.repositories.enums.StatusFlag;
import br.com.devmarco.services.UserService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    public Uni<UserDTO> create(UserDTO userDTO) {
        return userRepository.create(UserAdapter.convertTo(prepareUserCreation(userDTO)))
                .map(res -> UserAdapter.convertTo(res));
    }

    private UserDTO prepareUserCreation(UserDTO userDTO){
        userDTO.setId(UUID.randomUUID().toString());
        userDTO.setCreatedAt(LocalDate.now());
        userDTO.setStatus(StatusFlag.ACTIVE);

        return userDTO;
    }

    @Override
    public Uni<UserDTO> update(UserDTO userDTO) {
        return userRepository.update(UserAdapter.convertTo(userDTO))
                .map(res -> UserAdapter.convertTo(res));
    }

    @Override
    public Uni<UserDTO> delete(String nickname) {
        return userRepository.delete(nickname)
                .map(res -> UserAdapter.convertTo(res));
    }

    @Override
    public Uni<List<UserDTO>> findAll() {
        return userRepository.findAll()
                .map(res -> UserAdapter.convertListToDTO(res));
    }

    @Override
    public Uni<UserDTO> findByNickname(String nickname) {

        return userRepository.findByNickname(nickname)
                .map(res -> UserAdapter.convertTo(res));
    }
}
