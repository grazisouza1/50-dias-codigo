package com.semana6.bankapp;

import com.semana6.bankapp.menu.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAppApplication.class, args);
    }

    @Bean
    CommandLineRunner run (Menu menu) {
        return args -> {
            menu.startApplication();
        };
    }
}
