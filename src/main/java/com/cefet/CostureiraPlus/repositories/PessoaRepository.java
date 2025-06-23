package com.cefet.CostureiraPlus.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.CostureiraPlus.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);
    
    @Transactional
    void deleteByEmail(String email);
    @Transactional
    void deleteByCpf(String cpf);

}