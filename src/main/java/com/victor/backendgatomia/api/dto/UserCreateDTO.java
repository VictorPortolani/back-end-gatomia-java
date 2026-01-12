package com.victor.backendgatomia.api.dto;

import com.victor.backendgatomia.domain.model.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.Size;
import lombok.Data;

import org.hibernate.validator.constraints.br.CPF;

@Data
public class UserCreateDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    @Email
    private String email;

    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    private String phone;

    private UserRole role;
}
