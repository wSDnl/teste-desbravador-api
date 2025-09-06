package com.wssh.Application.Validate;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class Validate {

    private String value;

    public Validate(){}

    public boolean DataValida(Date data) {
        return data instanceof Date;
    }

    public boolean FloatMaiorQueZero(String valor) {
        try {
            float numero = Float.parseFloat(valor);
            return numero > 0.0f;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public boolean InteiroMaiorQueZero(String valor) {
        try {
            int numero = Integer.parseInt(valor);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean Nome(String nome) {
        String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+([ '-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nome);
        return matcher.matches();
    }

    public boolean RG(String rg) {
        String regex = "^\\d{2}\\.\\d{3}\\.\\d{3}-[\\dxX]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rg);
        return matcher.matches();
    }

    public boolean Telefone(String Telefone) {
        String regex = "^\\(\\d{2}\\)\\d{4}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Telefone);
        return matcher.matches();
    }

    public boolean Celular(String celular) {
        String regex = "^\\(\\d{2}\\)\\d{5}-\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(celular);
        return matcher.matches();
    }

    public boolean Email(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@([^\"'\\\\;]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean Senha(String senha) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)[^\"'\\\\;]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(senha);
        return matcher.matches();
    }

    public boolean Cpf(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os números são iguais (exemplo: 111.111.111-11)
        if (cpf.equals(cpf.substring(0, 1).repeat(11))) {
            return false;
        }

        // Validar o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 >= 10) digito1 = 0;

        // Validar o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 >= 10) digito2 = 0;

        // Verificar se os dígitos calculados são iguais aos dígitos fornecidos
        return cpf.charAt(9) == (char) ('0' + digito1) && cpf.charAt(10) == (char) ('0' + digito2);
    }


    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }   
}
