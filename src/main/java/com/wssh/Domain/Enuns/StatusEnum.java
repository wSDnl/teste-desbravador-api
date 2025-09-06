package com.wssh.Domain.Enuns;

import java.io.Serializable;
import org.springframework.stereotype.Service;

@Service
public class StatusEnum implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum TipoMensagem {
        EM_ANALISE("EM_ANALISE"),
        ANALISE_REALIZADA("ANALISE_REALIZADA"),
        ANALISE_APROVADA("ANALISE_APROVADA"),
        INICIADO("INICIADO"),
        PLANEJADO("PLANEJADO"),
        EM_ANDAMENTO("EM_ANDAMENTO"),
        ENCERRADO("ENCERRADO");
        private final String mensagem;

        TipoMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

}
