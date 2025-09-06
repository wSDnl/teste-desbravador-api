package com.wssh.Domain.Entities.Membros;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("gerente")
@Table(name = "membros_gerente")
@PrimaryKeyJoinColumn(name = "membro_id")
public class Gerente extends Membros {}
