package com.cefet.CostureiraPlus.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public PagamentoDTO insert(PagamentoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getIdPedido())
                .orElseThrow(
                        () -> new EntityNotFoundException("Pedido com ID: " + dto.getIdPedido() + " não encontrado"));

        Pagamento pagamento = new Pagamento();
        pagamento.setDataVencimento(dto.getData_vencimento());
        pagamento.setDataPagamento(dto.getData_pagamento());
        pagamento.setValor(dto.getValor());
        pagamento.setPedido(pedido);
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamentoSalvo);
    }

    // Atualizar Pagamento
    @Transactional
    public PagamentoDTO update(Long id, PagamentoDTO dto) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento com ID: " + id + " não encontrado."));

        if (dto.getIdPedido() != null && !dto.getIdPedido().equals(pagamento.getPedido().getId())) {
            throw new IllegalArgumentException("Não é permitido alterar o idPedido associado a uma parcela.");
        }
        if (Math.abs(pagamento.getValor() - dto.getValor()) > 0.001) {
            throw new IllegalArgumentException(
                    "Não é permitido alterar o valor de uma parcela. Esta operação deve ser feita no Pedido.");
        }
        pagamento.setDataVencimento(dto.getData_vencimento());
        pagamento.setDataPagamento(dto.getData_pagamento());
        Pagamento pagamentoAtualizado = pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamentoAtualizado);
    }

    @Transactional
    public PagamentoDTO registrarPagamento(Long id, LocalDate dataPagamento) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento com ID: " + id + " não encontrado."));
        if (dataPagamento == null) {
            throw new IllegalArgumentException("A data de pagamento não pode ser nula.");
        }
        pagamento.setDataPagamento(dataPagamento);
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
        return new PagamentoDTO(pagamentoSalvo);
    }

    // Remover por iD
    public void delete(Long id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pagamento não encontrada com ID: " + id);
        }
        pagamentoRepository.deleteById(id);
    }

    // Lista os pagamentos associados a pedidoId
    public List<PagamentoDTO> findPagamentosByPedidoId(Long pedidoId) {
        if (!pedidoRepository.existsById(pedidoId)) {
            throw new EntityNotFoundException("Pedido não encontrada com o ID: " + pedidoId);
        }
        List<Pagamento> pagamentos = pagamentoRepository.findByPedidoId(pedidoId);
        return pagamentos.stream().map(PagamentoDTO::new).toList();
    }
}