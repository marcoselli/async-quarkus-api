package br.com.devmarco.adapters;

import br.com.devmarco.repositories.models.UserEntity;
import br.com.devmarco.repositories.dtos.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserAdapter {

    public static UserDTO convertTo(UserEntity userEntity){
        return UserDTO.builder()
                .id(userEntity.getId())
                .nickname(userEntity.getNickname())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .status(userEntity.getStatus())
                .build();
    }

    public static List<UserDTO> convertListToDTO(List<UserEntity> userEntities){
        return userEntities.stream().map(userEntity -> convertTo(userEntity)).collect(Collectors.toList());
    }

    public static UserEntity convertTo(UserDTO userDTO){
        return UserEntity.builder()
                .id(userDTO.getId())
                .nickname(userDTO.getNickname())
                .password(userDTO.getPassword())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .status(userDTO.getStatus())
                .build();
    }

    public static List<UserEntity> convertListToUser(List<UserDTO> userDTOS){
        return userDTOS.stream().map(user -> convertTo(user)).collect(Collectors.toList());
    }
}
