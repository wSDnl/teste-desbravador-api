package com.wssh.Domain.Services.Membros;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wssh.Domain.Entities.Membros.Funcionario;
import com.wssh.Domain.Interfaces.Membros.IFuncionarioRepository;
import com.wssh.InfraData.DataConfiguration.Conn;
import com.wssh.InfraData.DataConfiguration.CrudDBImp;

@Service
public class FuncionarioService {

    @Autowired
    private final IFuncionarioRepository _IFuncionarioRepository;
    private final CrudDBImp crudDBImp;
    private final Conn conn;

    public FuncionarioService(IFuncionarioRepository IFuncionarioRepository, CrudDBImp crudDBImp, Conn conn){
        this._IFuncionarioRepository = IFuncionarioRepository;
        this.crudDBImp = crudDBImp;
        this.conn = conn;
    }

    public boolean Post(Funcionario entity){
        try {
            _IFuncionarioRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Funcionario GetById(Long id){
        try {
            return _IFuncionarioRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map<String, Object>> GetAll(){
        try {
            var query = crudDBImp.GetAllFuncionarios();
            return conn.executeQuery(query);
        } catch (Exception e) {
            return null;
        }
    }

}
