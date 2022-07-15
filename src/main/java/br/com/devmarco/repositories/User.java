package br.com.devmarco.repositories;

import br.com.devmarco.services.impl.AbstractUserServiceImpl;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@RegisterForReflection
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    private Long id;
    private String name;
    private String password;

    public static User from(Map<String, AttributeValue> item) {
        User user = new User();
        if (item != null && !item.isEmpty()) {
            user.setName(item.get(AbstractUserServiceImpl.USER_NAME_COL).getS());
            user.setPassword(item.get(AbstractUserServiceImpl.USER_PASSWORD_COL).getS());
        }
        return user;
    }
}
