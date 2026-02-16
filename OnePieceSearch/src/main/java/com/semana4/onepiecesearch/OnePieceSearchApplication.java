package com.semana4.onepiecesearch;

import com.semana4.onepiecesearch.menu.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnePieceSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnePieceSearchApplication.class, args);
    }

    @Bean
    CommandLineRunner run (Menu menu){
        return args -> {
            menu.startApplication();
        };

    }
}
