package com.cefet.CostureiraPlus.dto;

import java.time.LocalDate;

import com.cefet.CostureiraPlus.entities.Pedido;

public class PedidoDTO {

    private long id;
    private String descricao;
    private LocalDate data_pedido;
    private LocalDate data_entrega;
    private String status;
    private double valor;
    private String forma_pagamento;
    private int numero_parcelas;
    private Long idUsuarioCliente;
    private String nomeCliente;
    private Long idUsuarioCostureira;
    private String nomeCostureira;

    public PedidoDTO() {

    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.descricao = pedido.getDescricao();
        this.data_pedido = pedido.getDataPedido();
        this.data_entrega = pedido.getDataEntrega();
        this.status = pedido.getStatus();
        this.valor = pedido.getValor();
        this.forma_pagamento = pedido.getFormaPagamento();
        this.numero_parcelas = pedido.getNumeroParcelas();
        this.idUsuarioCliente = pedido.getUsuarioCliente().getId();
        this.nomeCliente = pedido.getUsuarioCliente().getPessoa().getNome();
        this.idUsuarioCostureira = pedido.getUsuarioCostureira().getId();
        this.nomeCostureira = pedido.getUsuarioCostureira().getPessoa().getNome();
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getData_pedido() {
        return data_pedido;
    }

    public LocalDate getData_entrega() {
        return data_entrega;
    }

    public String getStatus() {
        return status;
    }

    public double getValor() {
        return valor;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public int getNumero_parcelas() {
        return numero_parcelas;
    }

    public Long getIdUsuarioCliente() {
        return idUsuarioCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Long getIdUsuarioCostureira() {
        return idUsuarioCostureira;
    }

    public String getNomeCostureira() {
        return nomeCostureira;
    }

}
