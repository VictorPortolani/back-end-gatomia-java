package com.victor.backendgatomia.domain.service;

import com.victor.backendgatomia.api.dto.UserCreateDTO;
import com.victor.backendgatomia.api.dto.UserResponseDTO;
import com.victor.backendgatomia.domain.model.User;
import com.victor.backendgatomia.domain.model.enums.UserRole;
import com.victor.backendgatomia.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional // Garante o Rollback em caso de erro
    public UserResponseDTO createUser(UserCreateDTO dto) {

        // 1. Validações de Regra de Negócio
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }

        if (userRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("Já existe um usuário com este CPF.");
        }

        // 2. Conversão DTO -> Entity
        User newUser = new User();
        newUser.setName(dto.getName());
        newUser.setEmail(dto.getEmail());
        newUser.setCpf(dto.getCpf());
        newUser.setPhone(dto.getPhone());

        // Se não mandar Role, define como USER
        newUser.setRole(dto.getRole() != null ? dto.getRole() : UserRole.USER);

        // TODO: Futuramente usaremos BCrypt aqui. Por enquanto vai puro.
        newUser.setPasswordHash(dto.getPassword());

        // 3. Salvar no Banco
        User savedUser = userRepository.save(newUser);

        // 4. Retornar DTO de Resposta (sem senha)
        return new UserResponseDTO(savedUser);
    }
}