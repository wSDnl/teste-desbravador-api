package com.wssh.WebApi.Controllers.Membros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wssh.Application.Dto.ResponseDefaultDto;
import com.wssh.Application.Dto.Request.Membros.GerenteDto;
import com.wssh.Application.UseCase.MembrosUseCase;
import com.wssh.Domain.Entities.TokenClass;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Service
@RequestMapping("/api/membros/v1")
@Tag(name = "Membros", description = "Coleção Membros")
public class MembrosController {

    @Autowired
    private final TokenClass _Token;
    private final MembrosUseCase _UseCase;

    public MembrosController(TokenClass Token, MembrosUseCase UseCase){
        this._Token = Token;    
        this._UseCase = UseCase;
    }


    @GetMapping(value = "/gerentes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Todos os membros do tipo gerente", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> GetGerentes(
        @RequestHeader(name = "Bearer", required = true) String token) {
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.GetGerentes();
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    }

    @GetMapping(value = "/funcionarios", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Todos os membros do tipo funcionario", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> GetFuncionarios(
        @RequestHeader(name = "Bearer", required = true) String token) {
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.GetFuncionarios();
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    } 

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastro de Membros (GERENTE ou FUNCIONARIO)", description = "", responses = { @ApiResponse(description = "Success", responseCode = "200", content = { @Content( mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseDefaultDto.class)) ) }), 
    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = {@Content}), 
    @ApiResponse(description = "OK", responseCode = "200", content = {@Content}), 
    })
    public ResponseEntity<ResponseDefaultDto> PostMembro(
        @RequestHeader(name = "Bearer", required = true) String token,
        @RequestBody GerenteDto dto) {
        
        ResponseDefaultDto response = new ResponseDefaultDto();

        if (_Token.validaToken(token).equals(true)) {
            response = _UseCase.PostMembro(dto);
            return ResponseEntity.status(Integer.parseInt(response.getHttp())).body(response);
        }else{
            return ResponseEntity.status(402).body(response);
        }        
    }  

}
