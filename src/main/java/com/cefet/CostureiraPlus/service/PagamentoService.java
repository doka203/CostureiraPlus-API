package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.CostureiraPlus.dto.PagamentoDTO;
import com.cefet.CostureiraPlus.entities.Pagamento;
import com.cefet.CostureiraPlus.entities.Pedido;
import com.cefet.CostureiraPlus.repositories.PagamentoRepository;
import com.cefet.CostureiraPlus.repositories.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;


    // Buscar todos
    public List<PagamentoDTO> findAll() {
        List<Pagamento> listaPagamentos = pagamentoRepository.findAll();
        return listaPagamentos.stream().map(PagamentoDTO::new).toList();
    }

    // Buscar por iD
    public PagamentoDTO findById(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento com ID: " + id + " não encontrado."));
        return new PagamentoDTO(pagamento);
    }

    // Inserir Pagamento
    public PagamentoDTO insert(PagamentoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID: " + dto.getIdPedido()
                        + " não encontrado"));
        Pagamento pagamento = new Pagamento();
        pagamento.setDataVencimento(dto.getData_vencimento());
        pagamento.setDataPagamento(dto.getData_pagamento());
        pagamento.setValor(dto.getValor());
        pagamento.setPedido(pedido);
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamentoSalvo);
    }

    // Atualizar Pagamento
    public PagamentoDTO update(Long id, PagamentoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID: " + dto.getIdPedido()
                        + " não encontrado"));
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento com ID: " + id + " não encontrado."));
        pagamento.setDataVencimento(dto.getData_vencimento());
        pagamento.setDataPagamento(dto.getData_pagamento());
        pagamento.setValor(dto.getValor());
        pagamento.setPedido(pedido);
        Pagamento pagamentoAtualizado = pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamentoAtualizado);
    }

    // Remover por iD
    public void delete(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pagamento não encontrada com ID: " + id);
        }
        pagamentoRepository.deleteById(id);
    }
}