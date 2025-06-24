package com.cefet.CostureiraPlus.dto;

import java.time.LocalDate;

import com.cefet.CostureiraPlus.entities.Pagamento;

public class PagamentoDTO {

    private long id;
    private LocalDate data_vencimento;
    private LocalDate data_pagamento;
    private double valor;
    private Long idPedido;
    private String descricaoPedido;
    private LocalDate data_pedido;

    public PagamentoDTO() {

    }

    public PagamentoDTO(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.data_vencimento = pagamento.getDataVencimento();
        this.data_pagamento = pagamento.getDataPagamento();
        this.valor = pagamento.getValor();
        this.idPedido = pagamento.getPedido().getId();
        this.descricaoPedido = pagamento.getPedido().getDescricao();
        this.data_pedido = pagamento.getPedido().getDataPedido();
    }

    public long getId() {
        return id;
    }

    public LocalDate getData_vencimento() {
        return data_vencimento;
    }

    public LocalDate getData_pagamento() {
        return data_pagamento;
    }

    public double getValor() {
        return valor;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public String getDescricaoPedido() {
        return descricaoPedido;
    }

    public LocalDate getData_pedido() {
        return data_pedido;
    }

}
