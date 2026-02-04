package com.semana3.apicaller;

import com.semana3.apicaller.service.APIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ApiCallerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        APIService apiService = new APIService();

        System.out.println(apiService.getPilotName("Max"));
    }

}
