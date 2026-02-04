package com.semana3.apicaller.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("https://api.openf1.org/v1/")

public class APIController {

    @GetMapping
        public String sayHello() {
            return "Hello Woooorlldd!";
        }
}
