package com.wssh.Domain.Services.Membros;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wssh.Application.Dto.Request.Projetos.ProjetosMembrosDto;
import com.wssh.Domain.Entities.Projetos.Projetos;
import com.wssh.Domain.Interfaces.Membros.IProjetosRepository;
import com.wssh.InfraData.DataConfiguration.Conn;
import com.wssh.InfraData.DataConfiguration.CrudDBImp;

@Service
public class ProjetosService {

    @Autowired
    private final IProjetosRepository _IProjetosRepository;
    private final Conn conn;
	private final CrudDBImp crudDBImp;

    public ProjetosService(IProjetosRepository IProjetosRepository, CrudDBImp crudDBImp, Conn conn){
        this._IProjetosRepository = IProjetosRepository;
        this.conn = conn;
        this.crudDBImp = crudDBImp;
    }

    public List<Projetos> GetAll(){
        try {
            return _IProjetosRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean Post(Projetos entity){
        try {
            _IProjetosRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Delete(Projetos entity){
        try {
            _IProjetosRepository.delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Projetos GetById(Long id){
        try {
            return _IProjetosRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map<String, Object>> GetValidaQuantidade(ProjetosMembrosDto dto){                
        try {            
            var query = crudDBImp.GetValidaQuantidadeExecuteQuery(dto);
            return conn.executeQuery(query);                                            
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map<String, Object>> GetRelatorioResumido(){                
        try {            
            var query = crudDBImp.GetRelatorioResumidoExecuteQuery();
            return conn.executeQuery(query);                                            
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map<String, Object>> GetMembrosProjeto(Long id){                
        try {            
            var query = crudDBImp.GetMembrosProjetoExecuteQuery(id);
            return conn.executeQuery(query);                                            
        } catch (Exception e) {
            return null;
        }
    }

}
