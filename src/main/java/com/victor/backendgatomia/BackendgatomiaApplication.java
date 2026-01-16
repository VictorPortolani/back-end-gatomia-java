package com.victor.backendgatomia;

import jdk.jfr.Enabled;
import org.hibernate.annotations.EmbeddableInstantiator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendgatomiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendgatomiaApplication.class, args);
	}

}
