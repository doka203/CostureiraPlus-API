package com.cefet.CostureiraPlus.entities;

import java.time.LocalDate;

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
    private long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private LocalDate data_pedido;
    @Column(nullable = false)
    private LocalDate data_entrega;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private double valor;
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

    public Pedido(long id, String descricao, LocalDate dataPedido, LocalDate dataEntrega, String status,
                  String formaPagamento, int numeroParcelas, Usuario usuarioCliente, Usuario usuarioCostureira) {
        this.id = id;
        this.descricao = descricao;
        this.data_pedido = dataPedido;
        this.data_entrega = dataEntrega;
        this.status = status;
        this.forma_pagamento = formaPagamento;
        this.numero_parcelas = numeroParcelas;
        this.usuarioCliente = usuarioCliente;
        this.usuarioCostureira = usuarioCostureira;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataPedido() {
        return data_pedido;
    }

    public void setDataPedido(LocalDate data_pedido) {
        this.data_pedido = data_pedido;
    }

    public LocalDate getDataEntrega() {
        return data_entrega;
    }

    public void setDataEntrega(LocalDate data_entrega) {
        this.data_entrega = data_entrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
