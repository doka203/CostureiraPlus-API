package com.cefet.CostureiraPlus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.CostureiraPlus.entities.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
    List<Visita> findByUsuarioClienteId(Long usuarioId);
}
