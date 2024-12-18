package com.jujo2021.dotasksproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//No quiero que me pida credenciales

@SpringBootApplication
@EnableJpaAuditing
public class DotasksprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DotasksprojectApplication.class, args);
    }

}
