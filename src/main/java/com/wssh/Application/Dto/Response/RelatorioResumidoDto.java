package com.wssh.Application.Dto.Response;

import org.springframework.stereotype.Service;

@Service
public class RelatorioResumidoDto {
    private String qtd_projetos_por_status;
    private String total_orcado_por_status;
    private String media_dias_projetos_encerrados;
    private String total_membros_unicos;

    public String getQtd_projetos_por_status() {
        return qtd_projetos_por_status;
    }
    public void setQtd_projetos_por_status(String qtd_projetos_por_status) {
        this.qtd_projetos_por_status = qtd_projetos_por_status;
    }
    public String getTotal_orcado_por_status() {
        return total_orcado_por_status;
    }
    public void setTotal_orcado_por_status(String total_orcado_por_status) {
        this.total_orcado_por_status = total_orcado_por_status;
    }
    public String getMedia_dias_projetos_encerrados() {
        return media_dias_projetos_encerrados;
    }
    public void setMedia_dias_projetos_encerrados(String media_dias_projetos_encerrados) {
        this.media_dias_projetos_encerrados = media_dias_projetos_encerrados;
    }
    public String getTotal_membros_unicos() {
        return total_membros_unicos;
    }
    public void setTotal_membros_unicos(String total_membros_unicos) {
        this.total_membros_unicos = total_membros_unicos;
    }

}
