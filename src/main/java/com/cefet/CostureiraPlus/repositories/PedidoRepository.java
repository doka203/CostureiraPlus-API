package com.cefet.CostureiraPlus.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.CostureiraPlus.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuarioClienteId(Long usuarioId);
}
