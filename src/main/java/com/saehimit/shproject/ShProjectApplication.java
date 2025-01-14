package com.saehimit.shproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShProjectApplication.class, args);
    }

}
