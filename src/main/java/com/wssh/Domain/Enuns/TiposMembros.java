package com.wssh.Domain.Enuns;

import java.io.Serializable;
import org.springframework.stereotype.Service;

@Service
public class TiposMembros implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum TipoMensagem {
        GERENTE("GERENTE"),
        FUNCIONARIO("FUNCIONARIO");
        private final String mensagem;

        TipoMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public String getMensagem() {
            return mensagem;
        }
    }

}
