package com.cefet.CostureiraPlus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.CostureiraPlus.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByPessoaId(Long pessoaId);
}
