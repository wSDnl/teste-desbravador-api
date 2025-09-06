package com.wssh.Domain.Entities.Projetos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wssh.Domain.Entities.Membros.Gerente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "projetos")
public class Projetos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Date data_inicio;

    @Column(nullable = true)
    private Date previsao_termino;

    @Column(nullable = true)
    private Date data_real_termino;

    @Column(nullable = false)
    private BigDecimal orcamento_total;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "gerente_id" )    
    private Gerente gerente_responsavel;

    @Column(nullable = false)
    private String status_atual;

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjetosMembros> membros = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Gerente getGerente_responsavel() {
        return gerente_responsavel;
    }

    public void setGerente_responsavel(Gerente gerente_responsavel) {
        this.gerente_responsavel = gerente_responsavel;
    }

    public String getStatus_atual() {
        return status_atual;
    }

    public void setStatus_atual(String status_atual) {
        this.status_atual = status_atual;
    }

    

}
