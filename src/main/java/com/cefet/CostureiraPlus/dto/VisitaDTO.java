package com.cefet.CostureiraPlus.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cefet.CostureiraPlus.entities.Visita;

public class VisitaDTO {

    private long id;
    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private Long idUsuarioCliente;
    private String nomeCliente;
    private Long idUsuarioCostureira;
    private String nomeCostureira;

    public VisitaDTO() {

    }

    public VisitaDTO (Visita visita){
        this.id = visita.getId();
        this.data = visita.getData();
        this.hora = visita.getHora();
        this.descricao = visita.getDescricao();
        this.idUsuarioCliente = visita.getUsuarioCliente().getId();
        this.nomeCliente = visita.getUsuarioCliente().getPessoa().getNome();
        this.idUsuarioCostureira = visita.getUsuarioCostureira().getId();
        this.nomeCostureira = visita.getUsuarioCostureira().getPessoa().getNome();
    }

    public long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getDescricao() {
        return descricao;
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
