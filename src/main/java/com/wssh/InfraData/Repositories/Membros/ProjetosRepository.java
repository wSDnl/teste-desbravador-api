package com.wssh.InfraData.Repositories.Membros;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wssh.Application.Dto.ResponseDefaultDto;
import com.wssh.Application.Dto.Response.ProjetoMembrosResponseDto;
import com.wssh.Application.Dto.Response.RelatorioResumidoDto;
import com.wssh.Domain.Entities.Membros.Membros;
import com.wssh.Domain.Entities.Projetos.Projetos;
import com.wssh.Domain.Entities.Projetos.ProjetosMembros;
import com.wssh.Domain.Services.Membros.ProjetosMembrosService;
import com.wssh.Domain.Services.Membros.ProjetosService;

@Service
public class ProjetosRepository {

    Logger logger = Logger.getLogger(Membros.class.getName());

    @Autowired
    private final ProjetosService _ProjetosService;    
    private final ProjetosMembrosService _ProjetosMembrosService;
    private final ResponseDefaultDto _ResponseDefaultDto;

    public ProjetosRepository(ProjetosService ProjetosService, ResponseDefaultDto ResponseDefaultDto, ProjetosMembrosService ProjetosMembrosService){
        this._ProjetosService = ProjetosService;
        this._ProjetosMembrosService = ProjetosMembrosService;
        this._ResponseDefaultDto = ResponseDefaultDto;        
    }
    
    public ResponseDefaultDto GetMembrosProjeto(Long id){
        try {
            List<Map<String, Object>> result = _ProjetosService.GetMembrosProjeto(id);
            ArrayList<ProjetoMembrosResponseDto> lista = new ArrayList<>();
            for(Map<String, Object> row : result){
                ProjetoMembrosResponseDto projetoMembrosResponseDto = new ProjetoMembrosResponseDto();
                projetoMembrosResponseDto.setId(Long.parseLong( row.get("id").toString() ));
                projetoMembrosResponseDto.setNome(row.get("nome").toString());
                projetoMembrosResponseDto.setEmail(row.get("email").toString());
                lista.add(projetoMembrosResponseDto);
            }
            logger.info("[SUCESSO] -> GetMembrosProjeto ");
            return _ResponseDefaultDto.GetLista(lista);
        } catch (Exception e) {
            logger.info("[ERRO] -> GetMembrosProjeto -> " + e.getMessage());
            return _ResponseDefaultDto.GetLista(false);
        }
    }

    public ResponseDefaultDto GetAll(){
        try {                        
            logger.info("[SUCESSO] -> GetAll ");            
            List<Projetos> lista = _ProjetosService.GetAll();
            return _ResponseDefaultDto.GetLista(lista);
        } catch (Exception e) {
            logger.info("[ERRO] -> GetAll -> " + e.getMessage());
            return _ResponseDefaultDto.GetLista(false);
        }
    }

    public ResponseDefaultDto GetRelatorioResumido(){
        try {
            List<Map<String, Object>> result = _ProjetosService.GetRelatorioResumido();
            ArrayList<RelatorioResumidoDto> lista = new ArrayList<>();
            for(Map<String, Object> row : result){
                RelatorioResumidoDto relatorioResumidoDto = new RelatorioResumidoDto();
                relatorioResumidoDto.setQtd_projetos_por_status(row.get("qtd_projetos_por_status").toString());
                relatorioResumidoDto.setTotal_orcado_por_status(row.get("total_orcado_por_status").toString());
                relatorioResumidoDto.setMedia_dias_projetos_encerrados(row.get("media_dias_projetos_encerrados").toString());
                relatorioResumidoDto.setTotal_membros_unicos(row.get("total_membros_unicos").toString());
                lista.add(relatorioResumidoDto);
            }
            logger.info("[SUCESSO] -> GetRelatorioResumido ");
            return _ResponseDefaultDto.GetLista(lista);
        } catch (Exception e) {
            logger.info("[ERRO] -> GetRelatorioResumido -> " + e.getMessage());
            return _ResponseDefaultDto.GetLista(false);
        }
    }

    public ResponseDefaultDto PostProjeto(Projetos entity){         
        try {
            logger.info("[SUCESSO] -> PostProjeto ");
            boolean service = _ProjetosService.Post(entity);
            return _ResponseDefaultDto.PostBoolean(service);
        } catch (Exception e) {            
            logger.info("[ERRO] -> PostProjeto -> " + e.getMessage());
            return _ResponseDefaultDto.PostBoolean(false);
        }
    }

    public ResponseDefaultDto PostProjetoMembro(ProjetosMembros entity){         
        try {
            logger.info("[SUCESSO] -> PostProjetoMembro ");
            boolean service = _ProjetosMembrosService.Post(entity);
            return _ResponseDefaultDto.PostBoolean(service);
        } catch (Exception e) {            
            logger.info("[ERRO] -> PostProjetoMembro -> " + e.getMessage());
            return _ResponseDefaultDto.PostBoolean(false);
        }
    }

    public ResponseDefaultDto DeleteProjeto(Projetos entity){         
        try {
            logger.info("[SUCESSO] -> DeleteProjeto ");
            boolean service = _ProjetosService.Delete(entity);
            return _ResponseDefaultDto.PostBoolean(service);
        } catch (Exception e) {            
            logger.info("[ERRO] -> DeleteProjeto -> " + e.getMessage());
            return _ResponseDefaultDto.PostBoolean(false);
        }
    }

    public Projetos GetById(Long id){
        return _ProjetosService.GetById(id);
    }
}
