package com.wssh.Domain.Entities;

import org.springframework.stereotype.Service;

@Service
public class TokenClass {
    
    public Boolean validaToken(String token) {
        try {
            
            if (token.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")) {
                return true;
            } else {
                return false;
            }       

        } catch (Exception e) {
            System.out.println("Erro ao decodificar o payload: " + e.getMessage());
            return false; // Retorna JSON vazio em caso de erro
        }
    }

}
