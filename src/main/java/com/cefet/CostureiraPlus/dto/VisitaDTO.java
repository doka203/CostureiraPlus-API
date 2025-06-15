package com.cefet.CostureiraPlus.dto;

import com.cefet.CostureiraPlus.entities.Usuario;
import com.cefet.CostureiraPlus.entities.Visita;

public class VisitaDTO {

    private long id;
    private String data;
    private String hora;
    private String descricao;
    private Usuario usuarioCliente;
    private Usuario usuarioCostureira;

    public VisitaDTO() {

    }

    public VisitaDTO (Visita visita){
        this.id = visita.getId();
        this.data = visita.getData();
        this.hora = visita.getHora();
        this.descricao = visita.getDescricao();
        this.usuarioCliente = visita.getUsuarioCliente();
        this.usuarioCostureira = visita.getUsuarioCostureira();
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

    public Usuario getUsuarioCliente() {
        return usuarioCliente;
    }

    public Usuario getUsuarioCostureira() {
        return usuarioCostureira;
    }

}
