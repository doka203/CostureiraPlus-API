package com.cefet.CostureiraPlus.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.CostureiraPlus.dto.PedidoDTO;
import com.cefet.CostureiraPlus.entities.Pagamento;
import com.cefet.CostureiraPlus.entities.Pedido;
import com.cefet.CostureiraPlus.entities.Usuario;
import com.cefet.CostureiraPlus.repositories.PagamentoRepository;
import com.cefet.CostureiraPlus.repositories.PedidoRepository;
import com.cefet.CostureiraPlus.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;

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

    // Criar Pagamentos conforme o numero de parcelas de um pedido
    private void criarParcelas(Pedido pedido) {
        int numeroParcelas = pedido.getNumeroParcelas();
        if (numeroParcelas > 0 && pedido.getValor() > 0) {
            BigDecimal valorTotal = BigDecimal.valueOf(pedido.getValor());
            BigDecimal valorParcela = valorTotal.divide(BigDecimal.valueOf(numeroParcelas), 2, RoundingMode.HALF_UP);

            for (int i = 1; i <= numeroParcelas; i++) {
                Pagamento novaParcela = new Pagamento();
                novaParcela.setPedido(pedido);
                novaParcela.setValor(valorParcela.doubleValue());
                novaParcela.setDataVencimento(pedido.getDataPedido().plusMonths(i));
                novaParcela.setDataPagamento(null);
                pagamentoRepository.save(novaParcela);
            }
        }
    }

    // Inserir Pedido
    @Transactional
    public PedidoDTO insert(PedidoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.getIdUsuarioCliente())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente com ID: " + dto.getIdUsuarioCliente() + " não encontrado"));
        Usuario costureira = usuarioRepository.findById(dto.getIdUsuarioCostureira())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Costureira com ID: " + dto.getIdUsuarioCostureira() + " não encontrada"));

        Pedido pedido = new Pedido();
        pedido.setDescricao(dto.getDescricao());
        pedido.setDataPedido(dto.getData_pedido());
        pedido.setDataEntrega(dto.getData_entrega());
        pedido.setStatus(dto.getStatus());
        pedido.setValor(dto.getValor());
        pedido.setFormaPagamento(dto.getForma_pagamento());
        pedido.setNumeroParcelas(dto.getNumero_parcelas());
        pedido.setUsuarioCliente(cliente);
        pedido.setUsuarioCostureira(costureira);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        criarParcelas(pedidoSalvo);
        return new PedidoDTO(pedidoSalvo);
    }

    // Atualizar Pedido
    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.getIdUsuarioCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID: " + dto.getIdUsuarioCliente()
                        + " não encontrado"));
        Usuario costureira = usuarioRepository.findById(dto.getIdUsuarioCostureira())
                .orElseThrow(() -> new EntityNotFoundException("Costureira com ID: " + dto.getIdUsuarioCostureira()
                        + " não encontrada"));
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID: " + id + " não encontado."));
        double valorAntigo = pedido.getValor();
        int parcelasAntigas = pedido.getNumeroParcelas();
        pedido.setDescricao(dto.getDescricao());
        pedido.setDataPedido(dto.getData_pedido());
        pedido.setDataEntrega(dto.getData_entrega());
        pedido.setStatus(dto.getStatus());
        pedido.setValor(dto.getValor());
        pedido.setFormaPagamento(dto.getForma_pagamento());
        pedido.setNumeroParcelas(dto.getNumero_parcelas());
        pedido.setUsuarioCliente(cliente);
        pedido.setUsuarioCostureira(costureira);
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        boolean valorMudou = Double.compare(valorAntigo, pedidoAtualizado.getValor()) != 0;
        boolean parcelasMudaram = parcelasAntigas != pedidoAtualizado.getNumeroParcelas();
        if (valorMudou || parcelasMudaram) {
            pagamentoRepository.deleteByPedidoId(pedidoAtualizado.getId());
            criarParcelas(pedidoAtualizado);
        }
        return new PedidoDTO(pedidoAtualizado);
    }

    // Remover por iD
    @Transactional
    public void delete(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pedido com ID: " + id + " não encontrado.");
        }
        List<Pagamento> pagamentos = pagamentoRepository.findByPedidoId(id);
        if (!pagamentos.isEmpty()) {
            throw new DataIntegrityViolationException("Não é possível excluir o pedido com ID " + id
                    + " pois ele possui registros financeiros associados. Considere cancelar o pedido.");
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

    // Cancela um pedido alterando seu status para "CANCELADO"
    @Transactional
    public void cancelar(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido com ID: " + id + " não encontrado."));
        pedido.setStatus("CANCELADO");
        pedidoRepository.save(pedido);
    }
}
