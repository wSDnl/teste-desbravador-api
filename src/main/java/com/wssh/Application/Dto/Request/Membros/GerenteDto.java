package com.wssh.Application.Dto.Request.Membros;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.wssh.Application.Validate.Validate;

@Service
public class GerenteDto {

    private static Validate validate = new Validate();
    
    private String nome;
    private String email;
    private String tipo;

    public ArrayList<String> valida(GerenteDto entity){
        ArrayList<String> erros = new ArrayList<>();

        if (entity.nome == null || entity.nome.trim().isEmpty()){
            erros.add("NOME é obrigatório");
        } else {
            if (!validate.Nome(entity.nome)) {
                erros.add("NOME inválido");
            } else {            
                if (entity.nome.contains("'") || entity.nome.contains(";") || entity.nome.contains("--")) {
                    erros.add("NOME contém caracteres inválidos");
                }
            }
        }

        if (entity.email == null || entity.email.trim().isEmpty()){
            erros.add("E-MAIL é obrigatório");
        } else {
            if (!validate.Email(entity.email)) {
                erros.add("E-MAIL inválido");
            } else {            
                if (entity.email.contains("'") || entity.email.contains(";") || entity.email.contains("--")) {
                    erros.add("E-MAIL contém caracteres inválidos");
                }
            }
        }

        return erros;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    
}
