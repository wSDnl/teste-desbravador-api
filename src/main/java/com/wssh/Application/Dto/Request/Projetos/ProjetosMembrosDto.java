package com.wssh.Application.Dto.Request.Projetos;
import org.springframework.stereotype.Service;

@Service
public class ProjetosMembrosDto {
       
    private Long projeto;    
    private Long funcionario;

    public Long getProjeto() {
        return projeto;
    }
    public void setProjeto(Long projeto) {
        this.projeto = projeto;
    }
    public Long getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Long funcionario) {
        this.funcionario = funcionario;
    }    
}