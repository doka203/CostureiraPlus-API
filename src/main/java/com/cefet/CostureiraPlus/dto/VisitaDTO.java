package com.cefet.CostureiraPlus.dto;

import com.cefet.CostureiraPlus.entities.Visita;

public class VisitaDTO {

    private long id;
    private String data;
    private String hora;
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

    public String getData() {
        return data;
    }

    public String getHora() {
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
