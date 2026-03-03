package com.example.Test_SpringBoot.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MensagemRepository {
    public String obterMensagem() {
        return "Repository: Esta é a mensagem do repositório!";
    }
}
