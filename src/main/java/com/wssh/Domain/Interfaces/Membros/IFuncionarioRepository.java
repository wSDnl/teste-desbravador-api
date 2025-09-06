package com.wssh.Domain.Interfaces.Membros;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wssh.Domain.Entities.Membros.Funcionario;

public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long> {}