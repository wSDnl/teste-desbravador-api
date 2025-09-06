package com.wssh.InfraData.Repositories.Membros;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wssh.Application.Dto.ResponseDefaultDto;
import com.wssh.Domain.Entities.Membros.Funcionario;
import com.wssh.Domain.Entities.Membros.Gerente;
import com.wssh.Domain.Entities.Membros.Membros;
import com.wssh.Domain.Services.Membros.FuncionarioService;
import com.wssh.Domain.Services.Membros.GerenteService;

@Service
public class MembrosRepository {

    Logger logger = Logger.getLogger(Membros.class.getName());

    @Autowired
    private final GerenteService _GerenteService;
    private final FuncionarioService _FuncionarioService;
    private final ResponseDefaultDto _ResponseDefaultDto;

    public MembrosRepository(GerenteService GerenteService, ResponseDefaultDto ResponseDefaultDto, FuncionarioService FuncionarioService){
        this._GerenteService = GerenteService;
        this._ResponseDefaultDto = ResponseDefaultDto;
        this._FuncionarioService = FuncionarioService;
    }

    public ResponseDefaultDto GetGerentes(){
        List<Gerente> lista = new ArrayList<>();
        try {
            logger.info("[SUCESSO] -> GetGerentes ");
            List<Map<String, Object>> result = _GerenteService.GetAll();

            for(Map<String, Object> row : result){
                Gerente gerente = new Gerente();
                gerente.setId(Long.parseLong( row.get("id").toString() ));
                gerente.setNome(row.get("nome").toString());
                gerente.setEmail(row.get("email").toString());
                lista.add(gerente);
            }

            return _ResponseDefaultDto.PostResponseValida(lista);
        } catch (Exception e) {            
            logger.info("[ERRO] -> GetGerentes -> " + e.getMessage());
            return _ResponseDefaultDto.PostResponseValida(lista);
        }
    }

    public ResponseDefaultDto GetFuncionarios(){
        List<Funcionario> lista = new ArrayList<>();
        try {
            logger.info("[SUCESSO] -> GetFuncionarios ");
            List<Map<String, Object>> result = _FuncionarioService.GetAll();

            for(Map<String, Object> row : result){
                Funcionario gerente = new Funcionario();
                gerente.setId(Long.parseLong( row.get("id").toString() ));
                gerente.setNome(row.get("nome").toString());
                gerente.setEmail(row.get("email").toString());
                lista.add(gerente);
            }

            return _ResponseDefaultDto.PostResponseValidaFuncionario(lista);
        } catch (Exception e) {            
            logger.info("[ERRO] -> GetFuncionarios -> " + e.getMessage());
            return _ResponseDefaultDto.PostResponseValidaFuncionario(lista);
        }
    }

    public ResponseDefaultDto PostGerente(Gerente entity){
        try {
            logger.info("[SUCESSO] -> PostGerente ");
            boolean service = _GerenteService.Post(entity);
            return _ResponseDefaultDto.PostBoolean(service);
        } catch (Exception e) {            
            logger.info("[ERRO] -> PostGerente -> " + e.getMessage());
            return _ResponseDefaultDto.PostBoolean(false);
        }
    }

    public ResponseDefaultDto PostFuncionario(Funcionario entity){
        try {
            logger.info("[SUCESSO] -> PostFuncionario ");
            boolean service = _FuncionarioService.Post(entity);
            return _ResponseDefaultDto.PostBoolean(service);
        } catch (Exception e) {            
            logger.info("[ERRO] -> PostFuncionario -> " + e.getMessage());
            return _ResponseDefaultDto.PostBoolean(false);
        }
    }

    public Gerente GetGerente(Long id){
        return _GerenteService.GetById(id);
    }

    public Funcionario GetFuncionario(Long id){
        return _FuncionarioService.GetById(id);
    }

}
