package com.victor.backendgatomia.api.controller;

import com.victor.backendgatomia.domain.model.User;
import com.victor.backendgatomia.api.dto.TokenResponseDTO;
import com.victor.backendgatomia.api.dto.LoginDTO;
import com.victor.backendgatomia.domain.service.TokenService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO dto){
        //1. Encapsula as credenciais num objeto padrão String
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());

        //2. O gerente tenta fazer o login sozinho
        //Ele busca no banco, checa o hash e valida se a conta não está bloqueada
        var auth = authenticationManager.authenticate(usernamePassword);

        //3. Recupera o usuário que o gerente validou
        User user = (User) auth.getPrincipal();

        //4. Geramos o token
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
