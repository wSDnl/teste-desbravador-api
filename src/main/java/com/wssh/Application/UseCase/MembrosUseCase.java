package com.wssh.Application.UseCase;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wssh.Application.Dto.ResponseDefaultDto;
import com.wssh.Application.Dto.Request.Membros.GerenteDto;
import com.wssh.Domain.Entities.Membros.Funcionario;
import com.wssh.Domain.Entities.Membros.Gerente;
import com.wssh.Domain.Enuns.MessageEnum;
import com.wssh.Domain.Enuns.TiposMembros;
import com.wssh.InfraData.Repositories.Membros.MembrosRepository;

@Service
public class MembrosUseCase {

    @Autowired
    private final GerenteDto _GerenteDto;
    private final MembrosRepository _MembrosRepository;
    private final ResponseDefaultDto _ResponseDefaultDto;

    LocalDateTime localDateTime = LocalDateTime.now();
    private final Date _data = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    public MembrosUseCase(GerenteDto GerenteDto, ResponseDefaultDto ResponseDefaultDto, MembrosRepository MembrosRepository){
        this._GerenteDto = GerenteDto;
        this._ResponseDefaultDto = ResponseDefaultDto;
        this._MembrosRepository = MembrosRepository;
    }

    public ResponseDefaultDto PostMembro(GerenteDto dto){
        ArrayList<String> valida = _GerenteDto.valida(dto);                        

        if (valida.size() == 0) {                        
            if (dto.getTipo().equals(TiposMembros.TipoMensagem.GERENTE.getMensagem())){
                Gerente entity = this.MappingGerente(dto);
                return this.PostGerente(entity);
            }else if (dto.getTipo().equals(TiposMembros.TipoMensagem.FUNCIONARIO.getMensagem())){            
                Funcionario entity = this.MappingFuncionario(dto);
                return this.PostFuncionario(entity);
            } else{
                return _ResponseDefaultDto.ResponseGenerico("206", MessageEnum.TipoMensagem.TIPOS_MEMBRO_INCORRETO_INSERT.getMensagem());
            }
        }else{
            return _ResponseDefaultDto.PostResponseValida(valida);
        }        
    }

    private ResponseDefaultDto PostGerente(Gerente entity){
        return _MembrosRepository.PostGerente(entity);
    }

    private ResponseDefaultDto PostFuncionario(Funcionario entity){
        return _MembrosRepository.PostFuncionario(entity);
    }

    private Gerente MappingGerente(GerenteDto dto){
        Gerente gerente = new Gerente();
        gerente.setNome(dto.getNome().toUpperCase());
        gerente.setEmail(dto.getEmail().toLowerCase());
        gerente.setCreated_at(_data);
        gerente.setUpdated_at(_data);
        return gerente;
    }

    private Funcionario MappingFuncionario(GerenteDto dto){
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome().toUpperCase());
        funcionario.setEmail(dto.getEmail().toLowerCase());
        funcionario.setCreated_at(_data);
        funcionario.setUpdated_at(_data);
        return funcionario;
    }

    public ResponseDefaultDto GetGerentes(){
        return _MembrosRepository.GetGerentes();
    }

    public ResponseDefaultDto GetFuncionarios(){
        return _MembrosRepository.GetFuncionarios();
    }
}
