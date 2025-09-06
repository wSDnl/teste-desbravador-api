package com.wssh.Application.Dto;

import org.springframework.stereotype.Service;

@Service
public class ValidaQuantidadeDto {
    private int count_membros;
    private int count_status;
    
    public int getCount_membros() {
        return count_membros;
    }
    public void setCount_membros(int count_membros) {
        this.count_membros = count_membros;
    }
    public int getCount_status() {
        return count_status;
    }
    public void setCount_status(int count_status) {
        this.count_status = count_status;
    }

    

}
