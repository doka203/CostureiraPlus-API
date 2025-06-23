package com.cefet.CostureiraPlus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.CostureiraPlus.entities.Lembrete;

public interface LembreteRepository extends JpaRepository<Lembrete, Long> {
    List<Lembrete> findByPedidoId(Long pedidoId);
}
