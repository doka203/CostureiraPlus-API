package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.CostureiraPlus.dto.PedidoDTO;
import com.cefet.CostureiraPlus.entities.Pedido;
import com.cefet.CostureiraPlus.entities.Usuario;
import com.cefet.CostureiraPlus.repositories.PedidoRepository;
import com.cefet.CostureiraPlus.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Buscar todos
    public List<PedidoDTO> findAll() {
        List<Pedido> listaPedidos = pedidoRepository.findAll();
        return listaPedidos.stream().map(PedidoDTO::new).toList();
    }

    // Buscar por iD
    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID: " + id + " não encontrado."));
        return new PedidoDTO(pedido);
    }

    // Inserir Pedido
    public PedidoDTO insert(PedidoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.getIdUsuarioCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID: " + dto.getIdUsuarioCliente()
                        + " não encontrado"));
        Usuario costureira = usuarioRepository.findById(dto.getIdUsuarioCostureira())
                .orElseThrow(() -> new EntityNotFoundException("Costureira com ID: " + dto.getIdUsuarioCostureira()
                        + " não encontrada"));
        Pedido pedido = new Pedido();
        pedido.setDescricao(dto.getDescricao());
        pedido.setDataPedido(dto.getData_pedido());
        pedido.setDataEntrega(dto.getData_entrega());
        pedido.setStatus(dto.getStatus());
        pedido.setFormaPagamento(dto.getForma_pagamento());
        pedido.setNumeroParcelas(dto.getNumero_parcelas());
        pedido.setUsuarioCliente(cliente);
        pedido.setUsuarioCostureira(costureira);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return new PedidoDTO(pedidoSalvo);
    }

    // Atualizar Pedido
    public PedidoDTO update(Long id, PedidoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.getIdUsuarioCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID: " + dto.getIdUsuarioCliente()
                        + " não encontrado"));
        Usuario costureira = usuarioRepository.findById(dto.getIdUsuarioCostureira())
                .orElseThrow(() -> new EntityNotFoundException("Costureira com ID: " + dto.getIdUsuarioCostureira()
                        + " não encontrada"));
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID: " + id + " não encontado."));
        pedido.setDescricao(dto.getDescricao());
        pedido.setDataPedido(dto.getData_pedido());
        pedido.setDataEntrega(dto.getData_entrega());
        pedido.setStatus(dto.getStatus());
        pedido.setFormaPagamento(dto.getForma_pagamento());
        pedido.setNumeroParcelas(dto.getNumero_parcelas());
        pedido.setUsuarioCliente(cliente);
        pedido.setUsuarioCostureira(costureira);
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        return new PedidoDTO(pedidoAtualizado);
    }

    // Remover por iD
    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido não encontrada com ID: " + id);
        }
        pedidoRepository.deleteById(id);
    }

    // Listar pedidos associados a usuarioId
    public List<PedidoDTO> findPedidosByClienteId(Long usuarioId) {
    if (!usuarioRepository.existsById(usuarioId)) {
        throw new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId);
    }

    List<Pedido> pedidos = pedidoRepository.findByUsuarioClienteId(usuarioId);
    return pedidos.stream().map(PedidoDTO::new).toList();
}
}
