package com.cefet.CostureiraPlus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.CostureiraPlus.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}