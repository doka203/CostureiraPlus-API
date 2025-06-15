package com.cefet.CostureiraPlus.dto;

import com.cefet.CostureiraPlus.entities.Lembrete;
import com.cefet.CostureiraPlus.entities.Pedido;

public class LembreteDTO {

    private long id;
    private String descricao;
    private String data;
    private String hora;
    private String status;
    private Pedido pedido;

    public LembreteDTO() {

    }

    public LembreteDTO(Lembrete lembrete) {
        this.id = lembrete.getId();
        this.descricao = lembrete.getDescricao();
        this.data = lembrete.getData();
        this.hora = lembrete.getHora();
        this.status = lembrete.getStatus();
        this.pedido = lembrete.getPedido();
    }

    public long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public String getStatus() {
        return status;
    }

    public Pedido getPedido() {
        return pedido;
    }

}
