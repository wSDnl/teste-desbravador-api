package com.wssh.Application.UseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wssh.Application.Dto.ResponseDefaultDto;
import com.wssh.Application.Dto.ValidaQuantidadeDto;
import com.wssh.Application.Dto.Request.Projetos.ProjetosDto;
import com.wssh.Application.Dto.Request.Projetos.ProjetosMembrosDto;
import com.wssh.Domain.Entities.Membros.Funcionario;
import com.wssh.Domain.Entities.Membros.Gerente;
import com.wssh.Domain.Entities.Projetos.Projetos;
import com.wssh.Domain.Entities.Projetos.ProjetosMembros;
import com.wssh.Domain.Enuns.MessageEnum;
import com.wssh.Domain.Enuns.StatusEnum;
import com.wssh.Domain.Services.Membros.ProjetosService;
import com.wssh.InfraData.Repositories.Membros.MembrosRepository;
import com.wssh.InfraData.Repositories.Membros.ProjetosRepository;

@Service
public class ProjetosUseCase {

    @Autowired
    private final ProjetosRepository _ProjetosRepository;
    private final ProjetosService _ProjetosService;
    private final MembrosRepository _MembrosRepository;
    private final ResponseDefaultDto _ResponseDefaultDto;

    public ProjetosUseCase(ProjetosRepository ProjetosRepository, MembrosRepository MembrosRepository, ResponseDefaultDto ResponseDefaultDto, ProjetosService ProjetosService){
        this._ProjetosRepository = ProjetosRepository;
        this._ProjetosService = ProjetosService;
        this._MembrosRepository = MembrosRepository;
        this._ResponseDefaultDto = ResponseDefaultDto;
    }

    public ResponseDefaultDto GetMembrosProjeto(Long id){    
        return _ProjetosRepository.GetMembrosProjeto(id);
    }

    public ResponseDefaultDto GetAll(){    
        return _ProjetosRepository.GetAll();
    }

    public ResponseDefaultDto GetRelatorioResumido(){    
        return _ProjetosRepository.GetRelatorioResumido();
    }

    public ResponseDefaultDto PostProjeto(ProjetosDto dto){        
        Gerente gerente = _MembrosRepository.GetGerente(dto.getGerente_responsavel());                        
                
        if (gerente != null) {             
            Projetos entity = this.Mapper(dto, gerente);
            return _ProjetosRepository.PostProjeto(entity);    
        }else{
            return _ResponseDefaultDto.ResponseGenerico("206", MessageEnum.TipoMensagem.TIPOS_MEMBRO_INCORRETO.getMensagem());
        }
    }

    private Projetos Mapper(ProjetosDto dto, Gerente gerente){
        Projetos projeto = new Projetos();
        projeto.setNome(dto.getNome().toUpperCase());
        projeto.setData_inicio(dto.getData_inicio());
        projeto.setPrevisao_termino(dto.getPrevisao_termino());
        projeto.setData_real_termino(dto.getData_real_termino());
        projeto.setOrcamento_total(dto.getOrcamento_total());
        projeto.setDescricao(dto.getDescricao().toUpperCase());
        projeto.setStatus_atual(StatusEnum.TipoMensagem.EM_ANALISE.getMensagem());
        projeto.setGerente_responsavel(gerente);
        return projeto;        
    }

    public ResponseDefaultDto PostProjetoMembros(ProjetosMembrosDto dto){        
        Funcionario funcionario = _MembrosRepository.GetFuncionario(dto.getFuncionario());
        Projetos projeto = this.GetProjeto(dto.getProjeto());

        if (funcionario != null && projeto != null) {                            
            ArrayList<Object> valida = ValidaQuantidade(dto);
            ValidaQuantidadeDto validaDto = (ValidaQuantidadeDto) valida.get(0);            

            if (validaDto.getCount_membros() < 10 && validaDto.getCount_status() < 3) {
                ProjetosMembros entity = this.MapperProjetosMembros(projeto, funcionario);                
                return _ProjetosRepository.PostProjetoMembro(entity);    
            }else{
                return _ResponseDefaultDto.ResponseGenerico("206", "Membros deste grupo: " + validaDto.getCount_membros() + ",  Projetos simultaneamente com status diferente de encerrado ou cancelado: " + validaDto.getCount_status());    
            }

        }else{
            return _ResponseDefaultDto.ResponseGenerico("206", MessageEnum.TipoMensagem.TIPOS_MEMBRO_INCORRETO.getMensagem());
        }
    }

    private ArrayList<Object>  ValidaQuantidade(ProjetosMembrosDto dto){
        ArrayList<Object> lista = new ArrayList<>();        
            List<Map<String, Object>> result = _ProjetosService.GetValidaQuantidade(dto);        
            for(Map<String, Object> row : result){
                ValidaQuantidadeDto response = new ValidaQuantidadeDto();                                                                                
                response.setCount_membros(Integer.parseInt( row.get("count_membros").toString() ));
                response.setCount_status(Integer.parseInt( row.get("count_status").toString() ));                
                lista.add(response);
            }
        return lista; 
    }

    private ProjetosMembros MapperProjetosMembros(Projetos projeto, Funcionario funcionario){
        ProjetosMembros projetoMembro = new ProjetosMembros();
        projetoMembro.setFuncionario(funcionario);
        projetoMembro.setProjeto(projeto);
        return projetoMembro;
    }

    public Projetos GetProjeto(Long id){
        return _ProjetosService.GetById(id);
    }

    public ResponseDefaultDto DeleteProjeto(Long id){        
        Projetos projetos = _ProjetosRepository.GetById(id);
        if (projetos != null) {
            
            if (
                !projetos.getStatus_atual().toString().equals(StatusEnum.TipoMensagem.INICIADO.getMensagem().toString()) &&
                !projetos.getStatus_atual().toString().equals(StatusEnum.TipoMensagem.EM_ANALISE.getMensagem().toString()) &&
                !projetos.getStatus_atual().toString().equals(StatusEnum.TipoMensagem.ENCERRADO.getMensagem().toString())
            ) {                                
                return _ProjetosRepository.DeleteProjeto(projetos);
            }else{
                return _ResponseDefaultDto.ResponseGenerico("206", MessageEnum.TipoMensagem.ERRO_EXCLUIR_PEOJETO.getMensagem());
            }
        }else{
            return _ResponseDefaultDto.ResponseGenerico("206", MessageEnum.TipoMensagem.NAO_ENCONTRADO.getMensagem());
        }
    }

}
