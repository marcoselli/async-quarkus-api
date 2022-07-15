package br.com.devmarco.adapters;

import br.com.devmarco.repositories.User;
import br.com.devmarco.repositories.dtos.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserAdapter {

    public static UserDTO convertTo(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    public static List<UserDTO> convertListToDTO(List<User> users){
        return users.stream().map(user -> convertTo(user)).collect(Collectors.toList());
    }

    public static User convertTo(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
    }

    public static List<User> convertListToUser(List<UserDTO> userDTOS){
        return userDTOS.stream().map(user -> convertTo(user)).collect(Collectors.toList());
    }
}
