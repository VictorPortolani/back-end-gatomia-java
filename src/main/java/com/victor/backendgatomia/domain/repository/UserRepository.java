package com.victor.backendgatomia.domain.repository;

import com.victor.backendgatomia.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    //Métodos Mágicos
    //O Spring Data lê o nome do metodo e cria o SQL sozinho

    //metodo realiza a validação de where de busca se email existe
    boolean existsByEmail(String email);

    //metodo de validação que busca se cpf existe
    boolean existsByCpf(String cpf);


    // LOGIN (Futuro)
    // TODO: Descomentar abaixo quando formos implementar a autenticação (Login)
    // Optional<User> findByEmail(String email);

}
