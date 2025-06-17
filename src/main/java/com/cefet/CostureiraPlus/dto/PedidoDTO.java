package com.cefet.CostureiraPlus.dto;

import com.cefet.CostureiraPlus.entities.Pedido;
import com.cefet.CostureiraPlus.entities.Usuario;

public class PedidoDTO {

    private long id;
    private String descricao;
    private String data_pedido;
    private String data_entrega;
    private String status;
    private String forma_pagamento;
    private int numero_parcelas;
    private Usuario usuarioCLiente;
    private Usuario usuarioCostureira;

    public PedidoDTO() {

    }

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.descricao = pedido.getDescricao();
        this.data_pedido = pedido.getDataPedido();
        this.data_entrega = pedido.getDataEntrega();
        this.status = pedido.getStatus();
        this.forma_pagamento = pedido.getFormaPagamento();
        this.numero_parcelas = pedido.getNumeroParcelas();
        this.usuarioCLiente = pedido.getUsuarioCliente();
        this.usuarioCostureira = pedido.getUsuarioCostureira();
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData_pedido() {
        return data_pedido;
    }

    public String getData_entrega() {
        return data_entrega;
    }

    public String getStatus() {
        return status;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public int getNumero_parcelas() {
        return numero_parcelas;
    }

    public Usuario getUsuarioCliente() {
        return usuarioCLiente;
    }

    public Usuario getUsuarioCostureira() {
        return usuarioCostureira;
    }

}
