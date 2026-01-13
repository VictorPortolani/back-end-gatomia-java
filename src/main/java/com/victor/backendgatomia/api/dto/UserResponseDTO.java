package com.victor.backendgatomia.api.dto;

import com.victor.backendgatomia.domain.model.User;
import com.victor.backendgatomia.domain.model.enums.UserRole;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private String phone;
    private boolean isActive;
    private UserRole role;

    //Construtor converte a entidade em DTO (Facilita o service)
    public UserResponseDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.phone = user.getPhone();
        this.isActive = user.isActive();
        this.role = user.getRole();
    }
}
