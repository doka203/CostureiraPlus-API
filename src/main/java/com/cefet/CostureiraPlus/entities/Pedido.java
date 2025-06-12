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
    private String dataPedido;
    @Column(nullable = false)
    private String dataEntrega;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String formaPagamento;
    @Column(nullable = false)
    private int numeroParcelas;
    @ManyToOne
    @JoinColumn(name = "id_usuario_cliente")
    private Usuario usuarioCliente;
    @ManyToOne
    @JoinColumn(name = "id_usuario_costureira")
    private Usuario usuarioCostureira;

    public Pedido() {

    }

    public Pedido(int id, String descricao, String dataPedido, String dataEntrega, String status,
                  String formaPagamento, int numeroParcelas, Usuario usuarioCliente, Usuario usuarioCostureira) {
        this.id = id;
        this.descricao = descricao;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.status = status;
        this.formaPagamento = formaPagamento;
        this.numeroParcelas = numeroParcelas;
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
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
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
