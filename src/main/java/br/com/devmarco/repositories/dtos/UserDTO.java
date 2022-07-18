package br.com.devmarco.repositories.dtos;

import br.com.devmarco.repositories.enums.StatusFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {
    private String id;
    private String nickname;
    private String password;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private StatusFlag status;
}