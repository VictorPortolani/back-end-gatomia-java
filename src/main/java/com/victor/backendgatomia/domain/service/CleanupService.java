package com.victor.backendgatomia.domain.service;

import com.victor.backendgatomia.domain.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

public class CleanupService {
    private final UserRepository userRepository;

    public CleanupService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 3 * * *")//Roda sempre as 3 da manhã
    @Transactional //Importante para permitir o delete no banco
    public void limparUsuarioAntigo(){
        LocalDateTime trintaDiasAtras = LocalDateTime.now().minusDays(30);

        System.out.println("Inciando varredura...");

        userRepository.deleteByDeletedAtBefore(trintaDiasAtras);

        System.out.println("Limpeza finalizada");
    }
}
