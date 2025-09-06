package com.wssh.InfraData.DataConfiguration;

import java.io.Serializable;
import org.springframework.stereotype.Service;
import com.wssh.Application.Dto.Request.Projetos.ProjetosMembrosDto;

@Service
public class CrudDBImp implements Serializable {

	private static final long serialVersionUID = 1L;

	public String GetValidaQuantidadeExecuteQuery(ProjetosMembrosDto dto){						
		String query = String.format("""
			SELECT (SELECT count(projetos.id) as count_membros
			FROM projetos
			INNER JOIN projetos_membros on projetos_membros.projeto_id = projetos.id 
			WHERE projetos.id = %d
			) AS count_membros,
			(SELECT count(projetos.id) as count_membros
			FROM projetos
			INNER JOIN projetos_membros on projetos_membros.projeto_id = projetos.id
			WHERE projetos.status_atual NOT IN ('ENCERRADO', 'CANCELADO') 
			AND funcionario_id = %d
			) as count_status
		""", dto.getProjeto(), dto.getFuncionario());
		return query;
	}

	public String GetRelatorioResumidoExecuteQuery(){						
		String query = String.format("""
			SELECT
			(SELECT GROUP_CONCAT(CONCAT(status_atual, ': ', qtd) SEPARATOR ', ')
			FROM (
			SELECT p.status_atual, COUNT(*) AS qtd
			FROM projetos p
			GROUP BY p.status_atual
			) AS sub1) AS qtd_projetos_por_status,
			(SELECT GROUP_CONCAT(CONCAT(status_atual, ': R$ ', total_orcamento) SEPARATOR ', ')
			FROM (
			SELECT p.status_atual, SUM(p.orcamento_total) AS total_orcamento
			FROM projetos p
			GROUP BY p.status_atual
			) AS sub2) AS total_orcado_por_status,
			(SELECT AVG(DATEDIFF(p.data_real_termino, p.data_inicio))
			FROM projetos p
			WHERE p.status_atual = 'ENCERRADO') AS media_dias_projetos_encerrados,
			(SELECT COUNT(DISTINCT pm.funcionario_id)
			FROM projetos_membros pm) AS total_membros_unicos;
		""");
		return query;
	}

	public String GetAll(){						
		String query = String.format("""
			SELECT membros, id, created_at, email, nome, updated_at
			FROM membros
			WHERE membros.membros = 'gerente'			
		""");
		return query;
	}

	public String GetAllFuncionarios(){						
		String query = String.format("""
			SELECT membros, id, created_at, email, nome, updated_at
			FROM membros
			WHERE membros.membros = 'funcionario'			
		""");
		return query;
	}

	public String GetMembrosProjetoExecuteQuery(Long id){						
		String query = String.format("""
			SELECT membros.id, membros.nome, membros.email FROM projetos_membros
			INNER JOIN membros on membros.id = projetos_membros.funcionario_id
			WHERE projetos_membros.projeto_id = %d			
		""", id);
		return query;
	}
	
}

