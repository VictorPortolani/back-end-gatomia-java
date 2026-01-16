package com.victor.backendgatomia.api.controller;

import com.victor.backendgatomia.api.dto.UserCreateDTO;
import com.victor.backendgatomia.api.dto.UserResponseDTO;
import com.victor.backendgatomia.api.dto.UserUpdateDTO;
import com.victor.backendgatomia.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // 1. Diz que é um controller rest (retorna JSON)
@RequestMapping("/users") //2. Define a rota
public class UserController {
    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping //3. Mapeia para o verbo POST
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserCreateDTO dto){
        //Chama o service(Regra de negócio)
        UserResponseDTO userCreated = userService.createUser(dto);

        //Retorna status 201(Created) e o corpo dos dados
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll(){
        List<UserResponseDTO> users = userService.getAllUsers();

        //Retorna status 200 ok
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")//A URL será /users/id_usuario
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO dto){
        UserResponseDTO updateUser = userService.updateUser(id, dto);

        return ResponseEntity.ok(updateUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
