package com.wssh.Domain.Services.Membros;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wssh.Domain.Entities.Membros.Gerente;
import com.wssh.Domain.Interfaces.Membros.IGerenteRepository;
import com.wssh.InfraData.DataConfiguration.Conn;
import com.wssh.InfraData.DataConfiguration.CrudDBImp;

@Service
public class GerenteService {

    @Autowired
    private final IGerenteRepository _IGerenteRepository;
    private final CrudDBImp crudDBImp;
    private final Conn conn;

    public GerenteService(IGerenteRepository GerenteRepository, CrudDBImp crudDBImp, Conn conn){
        this._IGerenteRepository = GerenteRepository;
        this.crudDBImp = crudDBImp;
        this.conn = conn;
    }

    public boolean Post(Gerente entity){
        try {
            _IGerenteRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Gerente GetById(Long id){
        try {
            return _IGerenteRepository.findById(id).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map<String, Object>> GetAll(){
        try {
            var query = crudDBImp.GetAll();
            return conn.executeQuery(query);
        } catch (Exception e) {
            return null;
        }
    }

}
