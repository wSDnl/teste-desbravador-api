package com.wssh.WebApi.Controllers.Projetos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wssh.Application.Dto.ResponseDefaultDto;
import com.wssh.Application.Dto.Request.Projetos.ProjetosDto;
import com.wssh.Application.Dto.Request.Projetos.ProjetosMembrosDto;
import com.wssh.Application.UseCase.ProjetosUseCase;
import com.wssh.Domain.Entities.TokenClass;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Service
@RequestMapping("/api/projetos/v1")
@Tag(name = "Projetos", description = "Coleção Projetos")
public class ProjetosController {

    @Autowired
    private final TokenClass _Token;
    private final ProjetosUseCase _UseCase;

    public ProjetosController(TokenClass Token, ProjetosUseCase UseCase){
        this._Token = Token;    
        this._UseCase = UseCase;
    }

    @GetMapping(value = "/membros/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Membros por projeto, por id do projeto", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> GetMembrosProjeto(
        @RequestHeader(name = "Bearer", required = true) String token,
        @PathVariable(name = "id") Long id) {
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.GetMembrosProjeto(id);
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Todos os projetos", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> GetAll(
        @RequestHeader(name = "Bearer", required = true) String token) {
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.GetAll();
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    }  

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Relatório resumido", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> GetRelatorioResumido(
        @RequestHeader(name = "Bearer", required = true) String token) {
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.GetRelatorioResumido();
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    }  

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastro de Projetos", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> PostProjeto(
        @RequestHeader(name = "Bearer", required = true) String token,
        @RequestBody ProjetosDto dto) {
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.PostProjeto(dto);
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    }  

    @PostMapping(value = "/projetos/membros", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastro de Membros em Projetos", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> PostProjetoMembros(
        @RequestHeader(name = "Bearer", required = true) String token,
        @RequestBody ProjetosMembrosDto dto) {
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.PostProjetoMembros(dto);
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    }  

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Excluir projeto", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> DeleteProjeto(
        @RequestHeader(name = "Bearer", required = true) String token,
        @PathVariable(name = "id") Long id){
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.DeleteProjeto(id);
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    }          
}
