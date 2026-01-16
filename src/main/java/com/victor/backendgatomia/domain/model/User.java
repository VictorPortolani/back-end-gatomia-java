package com.victor.backendgatomia.domain.model;

import com.victor.backendgatomia.domain.model.enums.UserRole;

import jakarta.persistence.*;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // 1. Diz que isso é uma tabela no banco SQL
@Table(name = "tb_users") //2. Declara o nome da tabela
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        if(this.role == UserRole.ADMIN){
            //Admin tem controle total
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_MODERATOR"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        if(this.role == UserRole.MODERATOR){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_MODERATOR"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword(){
        return passwordHash;
    }

    @Override
    public String getUsername(){
        return email; //Retorna o email como login
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Se deletedAt estiver vazio (null), o usuário está ativo
        return deletedAt == null;
    }
}
