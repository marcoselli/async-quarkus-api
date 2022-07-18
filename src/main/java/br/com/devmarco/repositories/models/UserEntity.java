package br.com.devmarco.repositories.models;

import br.com.devmarco.repositories.enums.StatusFlag;
import br.com.devmarco.repositories.impl.AbstractUserRepository;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.LocalDate;
import java.util.Map;

@RegisterForReflection
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity {
    private String id;
    private String nickname;
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private StatusFlag status;

    public static UserEntity from(Map<String, AttributeValue> item) {
        UserEntity userEntity = new UserEntity();
        if (item != null && !item.isEmpty()) {
            userEntity.setNickname(item.get(AbstractUserRepository.USER_NAME_COL).s());
            userEntity.setPassword(item.get(AbstractUserRepository.USER_PASSWORD_COL).s());
        }
        return userEntity;
    }
}
