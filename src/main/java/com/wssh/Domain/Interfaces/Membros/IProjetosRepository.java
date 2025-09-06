package com.wssh.Domain.Interfaces.Membros;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wssh.Domain.Entities.Projetos.Projetos;

public interface IProjetosRepository extends JpaRepository<Projetos, Long> {}