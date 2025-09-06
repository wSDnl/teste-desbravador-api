package com.wssh.Application.Dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wssh.Domain.Entities.Membros.Funcionario;
import com.wssh.Domain.Entities.Membros.Gerente;
import com.wssh.Domain.Enuns.MessageEnum;

@Service
public class ResponseDefaultDto implements Serializable {

	private static final long serialVersionUID = 1L;

	String http;
	String message;	
	Object data;

	public ResponseDefaultDto GetLista(Object data){
		if (data == null) {
			setHttp("206");
            setMessage(MessageEnum.TipoMensagem.ERRO_INTERNO.getMensagem());
            setData(false);
		}else{	
			setHttp("200");
			setMessage(MessageEnum.TipoMensagem.SUCESSO.getMensagem());
			setData(data);
		}
		return this;
	}

	public ResponseDefaultDto PostResponseValida(ArrayList<String> valida){
		if (valida.size() > 0) {
			setHttp("206");
			setMessage(MessageEnum.TipoMensagem.ERRO_INTERNO.getMensagem());
			setData(valida);
		}else{
			setHttp("200");
			setMessage(MessageEnum.TipoMensagem.SUCESSO.getMensagem());
			setData(true);
		}
		return this;
	}

	public ResponseDefaultDto PostResponseValida(List<Gerente> data){
		if (data.size() == 0) {
			setHttp("206");
			setMessage(MessageEnum.TipoMensagem.ERRO_INTERNO.getMensagem());
			setData(false);
		}else{
			setHttp("200");
			setMessage(MessageEnum.TipoMensagem.SUCESSO.getMensagem());
			setData(data);
		}
		return this;
	}

	public ResponseDefaultDto PostResponseValidaFuncionario(List<Funcionario> data){
		if (data.size() == 0) {
			setHttp("206");
			setMessage(MessageEnum.TipoMensagem.ERRO_INTERNO.getMensagem());
			setData(false);
		}else{
			setHttp("200");
			setMessage(MessageEnum.TipoMensagem.SUCESSO.getMensagem());
			setData(data);
		}
		return this;
	}

	public ResponseDefaultDto PostBoolean(Boolean value){
		if (value.equals(true)) {
			setHttp("200");
			setMessage(MessageEnum.TipoMensagem.SUCESSO.getMensagem());
			setData(value);
		}else{
			setHttp("206");
			setMessage(MessageEnum.TipoMensagem.ERRO_INTERNO.getMensagem());
			setData(value);
		}
		return this;
	}

	public ResponseDefaultDto ResponseGenerico(String http, String message){		
		setHttp(http);
		setMessage(message);
		setData(false);		
		return this;
	}


	public String getHttp() {
		return http;
	}
	public void setHttp(String http) {
		this.http = http;
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
