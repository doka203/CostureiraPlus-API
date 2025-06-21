package com.cefet.CostureiraPlus.dto;

import com.cefet.CostureiraPlus.entities.Lembrete;

public class LembreteDTO {

    private long id;
    private String descricao;
    private String data;
    private String hora;
    private String status;
    private Long idPedido;
    private String descricaoPedido;

    public LembreteDTO() {

    }

    public LembreteDTO(Lembrete lembrete) {
        this.id = lembrete.getId();
        this.descricao = lembrete.getDescricao();
        this.data = lembrete.getData();
        this.hora = lembrete.getHora();
        this.status = lembrete.getStatus();
        this.idPedido = lembrete.getPedido().getId();
        this.descricaoPedido = lembrete.getPedido().getDescricao();
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

    public Long getIdPedido() {
        return idPedido;
    }

    public String getDescricaoPedido() {
        return descricaoPedido;
    }


}
