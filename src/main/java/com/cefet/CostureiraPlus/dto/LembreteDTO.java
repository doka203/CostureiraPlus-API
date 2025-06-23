package com.cefet.CostureiraPlus.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cefet.CostureiraPlus.entities.Lembrete;

public class LembreteDTO {

    private long id;
    private String descricao;
    private LocalDate data;
    private LocalTime hora;
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

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
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
