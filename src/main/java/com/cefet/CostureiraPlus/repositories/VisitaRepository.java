package com.cefet.CostureiraPlus.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cefet.CostureiraPlus.entities.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Long> {
    List<Visita> findByUsuarioClienteId(Long usuarioId);

    @Query("SELECT v FROM Visita v WHERE v.data BETWEEN :dataInicio AND :dataFim " +
            "AND (:clienteId IS NULL OR v.usuarioCliente.id = :clienteId) " +
            "ORDER BY v.data ASC, v.hora ASC")
    List<Visita> findVisitasComFiltros(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("clienteId") Long clienteId);
}
