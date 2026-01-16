package com.victor.backendgatomia.domain.model;

import com.victor.backendgatomia.domain.model.enums.UserRole;

import jakarta.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // 1. Diz que isso é uma tabela no banco SQL
@Table(name = "tb_users") //2. Declara o nome da tabela
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)// define nome como obrigatório
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    private String phone;

    @Column(name = "date_registration", nullable = false)
    private LocalDateTime dateRegistration = LocalDateTime.now();

    private LocalDateTime deletedAt; //null = ativo

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role = UserRole.USER;


}
