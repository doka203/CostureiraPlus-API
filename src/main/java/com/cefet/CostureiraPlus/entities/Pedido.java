package com.cefet.CostureiraPlus.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private String data_pedido;
    @Column(nullable = false)
    private String data_entrega;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String forma_pagamento;
    @Column(nullable = false)
    private int numero_parcelas;
    @ManyToOne
    @JoinColumn(name = "id_usuario_cliente")
    private Usuario usuarioCliente;
    @ManyToOne
    @JoinColumn(name = "id_usuario_costureira")
    private Usuario usuarioCostureira;

    public Pedido() {

    }

    public Pedido(int id, String descricao, String data_pedido, String data_entrega, String status,
                  String forma_pagamento, int numero_parcelas, Usuario usuarioCliente, Usuario usuarioCostureira) {
        this.id = id;
        this.descricao = descricao;
        this.data_pedido = data_pedido;
        this.data_entrega = data_entrega;
        this.status = status;
        this.forma_pagamento = forma_pagamento;
        this.numero_parcelas = numero_parcelas;
        this.usuarioCliente = usuarioCliente;
        this.usuarioCostureira = usuarioCostureira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataPedido() {
        return data_pedido;
    }

    public void setDataPedido(String data_pedido) {
        this.data_pedido = data_pedido;
    }

    public String getDataEntrega() {
        return data_entrega;
    }

    public void setDataEntrega(String data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormaPagamento() {
        return forma_pagamento;
    }

    public void setFormaPagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public int getNumeroParcelas() {
        return numero_parcelas;
    }

    public void setNumeroParcelas(int numero_parcelas) {
        this.numero_parcelas = numero_parcelas;
    }

    public Usuario getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(Usuario usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public Usuario getUsuarioCostureira() {
        return usuarioCostureira;
    }

    public void setUsuarioCostureira(Usuario usuarioCostureira) {
        this.usuarioCostureira = usuarioCostureira;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

}
