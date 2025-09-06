package com.wssh.Domain.Entities;

import org.springframework.stereotype.Service;

@Service
public class TokenClass {
    
    public Boolean validaToken(String token) {
        try {
            
            if (token.equals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzaXN0ZW1hLm1lc2FkYSIsIm5hbWUiOiIxLjEuMS4xLjEiLCJpYXQiOjEzMjQzNTQ2fQ.B8MutbWuoArovX6R0H8VwVe82U8uCDLow3efPo2flDo")) {
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
