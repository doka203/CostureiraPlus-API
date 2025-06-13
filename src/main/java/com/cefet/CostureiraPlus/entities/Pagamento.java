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
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String data_vencimento;
    @Column(nullable = false)
    private String data_pagamento;
    @Column(nullable = false)
    private double valor;
    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public Pagamento() {

    }

    public Pagamento(int id, String data_vencimento, String data_pagamento, double valor, Pedido pedido) {
        this.id = id;
        this.data_vencimento = data_vencimento;
        this.data_pagamento = data_pagamento;
        this.valor = valor;
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataVencimento() {
        return data_vencimento;
    }

    public void setDataVencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getDataPagamento() {
        return data_pagamento;
    }

    public void setDataPagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
