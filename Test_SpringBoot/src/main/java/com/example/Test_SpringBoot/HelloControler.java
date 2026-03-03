package com.example.Test_SpringBoot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class HelloControler {
    
    @GetMapping("/hello")
    public String hello() {
        return "Bem vindo";
    }
    
}
