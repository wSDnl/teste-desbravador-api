package com.wssh.Domain.Enuns;

import java.io.Serializable;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class MessageEnum implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum TipoMensagem {
        SUCESSO("Operação realizada com sucesso."),
        NAO_ENCONTRADO("Recurso solicitado não foi encontrado."),
        ERRO_INTERNO("Erro interno no servidor. Por favor, tente novamente mais tarde."),
        TIPOS_MEMBRO_INCORRETO_INSERT("Tipo de membro incorreto, utilize:" + Arrays.toString(TiposMembros.TipoMensagem.values())),
        STATUS_INCORRETO("Status incorreto, utilize:" + Arrays.toString(StatusEnum.TipoMensagem.values())),
        ERRO_EXCLUIR_PEOJETO("O projeto não pode estar com status " + StatusEnum.TipoMensagem.INICIADO.getMensagem() +", "+ StatusEnum.TipoMensagem.EM_ANALISE.getMensagem()  +" ou "+  StatusEnum.TipoMensagem.ENCERRADO.getMensagem()),
        TIPOS_MEMBRO_INCORRETO("Tipo de Membro incorreto");

        private final String mensagem;

        TipoMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

}
