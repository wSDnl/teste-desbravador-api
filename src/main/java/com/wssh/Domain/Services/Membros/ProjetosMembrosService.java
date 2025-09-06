package com.wssh.Domain.Services.Membros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wssh.Domain.Entities.Projetos.ProjetosMembros;
import com.wssh.Domain.Interfaces.Membros.IProjetosMembrosRepository;

@Service
public class ProjetosMembrosService {

    @Autowired
    private final IProjetosMembrosRepository _IProjetosMembrosRepository;

    public ProjetosMembrosService(IProjetosMembrosRepository IProjetosMembrosRepository){
        this._IProjetosMembrosRepository = IProjetosMembrosRepository;
    }

    public boolean Post(ProjetosMembros entity){
        try {
            _IProjetosMembrosRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
