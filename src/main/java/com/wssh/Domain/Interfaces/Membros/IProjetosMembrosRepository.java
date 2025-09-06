package com.wssh.Domain.Interfaces.Membros;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wssh.Domain.Entities.Projetos.ProjetosMembros;

public interface IProjetosMembrosRepository extends JpaRepository<ProjetosMembros, Long> {}