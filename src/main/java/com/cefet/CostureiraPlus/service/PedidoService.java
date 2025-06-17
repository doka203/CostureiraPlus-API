package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cefet.CostureiraPlus.dto.PedidoDTO;
import com.cefet.CostureiraPlus.entities.Pedido;
import com.cefet.CostureiraPlus.repositories.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;

public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<PedidoDTO> findAll() {
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        return listaPedidos.stream().map(PedidoDTO::new).toList();
    }

    //Buscar por iD
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido com ID: " + id + " não encontrado."));
        return new PedidoDTO(pedido);
    }

    //Inserir Pedido
    public PedidoDTO insert(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setDescricao(dto.getDescricao());
        pedido.setDataPedido(dto.getData_pedido());
        pedido.setDataEntrega(dto.getData_entrega());
        pedido.setStatus(dto.getStatus());
        pedido.setFormaPagamento(dto.getForma_pagamento());
        pedido.setNumeroParcelas(dto.getNumero_parcelas());
        pedido.setUsuarioCliente(dto.getUsuarioCliente());
        pedido.setUsuarioCostureira(dto.getUsuarioCostureira());
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return new PedidoDTO(pedidoSalvo);
    }

    //Atualizar Pedido
    public PedidoDTO update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pedido com ID: " + id + " não encontado."));
        pedido.setDescricao(dto.getDescricao());
        pedido.setDataPedido(dto.getData_pedido());
        pedido.setDataEntrega(dto.getData_entrega());
        pedido.setStatus(dto.getStatus());
        pedido.setFormaPagamento(dto.getForma_pagamento());
        pedido.setNumeroParcelas(dto.getNumero_parcelas());
        pedido.setUsuarioCliente(dto.getUsuarioCliente());
        pedido.setUsuarioCostureira(dto.getUsuarioCostureira());
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return new PedidoDTO(pedidoAtualizado);
    }

    //Remover por iD
    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido não encontrada com ID: " + id);
        }
        pedidoRepository.deleteById(id);
    }
}
