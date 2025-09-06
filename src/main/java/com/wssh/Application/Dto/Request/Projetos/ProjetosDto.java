package com.wssh.Application.Dto.Request.Projetos;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProjetosDto {
    
    private String nome;    

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data_inicio;    

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date previsao_termino;    

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date data_real_termino;    
    private BigDecimal orcamento_total;    
    private String descricao;    
    private Long gerente_responsavel;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Date getData_inicio() {
        return data_inicio;
    }
    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }
    public Date getPrevisao_termino() {
        return previsao_termino;
    }
    public void setPrevisao_termino(Date previsao_termino) {
        this.previsao_termino = previsao_termino;
    }
    public Date getData_real_termino() {
        return data_real_termino;
    }
    public void setData_real_termino(Date data_real_termino) {
        this.data_real_termino = data_real_termino;
    }
    public BigDecimal getOrcamento_total() {
        return orcamento_total;
    }
    public void setOrcamento_total(BigDecimal orcamento_total) {
        this.orcamento_total = orcamento_total;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Long getGerente_responsavel() {
        return gerente_responsavel;
    }
    public void setGerente_responsavel(Long gerente_responsavel) {
        this.gerente_responsavel = gerente_responsavel;
    }    
    
    

}