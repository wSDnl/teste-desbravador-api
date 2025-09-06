package com.wssh.Domain.Entities.Membros;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("funcionario")
@Table(name = "membros_funcinario")
@PrimaryKeyJoinColumn(name = "membro_id")
public class Funcionario extends Membros {}
