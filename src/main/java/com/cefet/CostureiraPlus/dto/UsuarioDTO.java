package com.cefet.CostureiraPlus.dto;

import com.cefet.CostureiraPlus.entities.Usuario;

public class UsuarioDTO {

    private long id;
    private String login;
    private String senha;
    private String tipo;
    private String cpf;
    private String nome;

    public UsuarioDTO() {
        
    }

    public UsuarioDTO (Usuario usuario){
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.tipo = usuario.getTipo();
        this.cpf = usuario.getPessoa().getCpf();
        this.nome = usuario.getPessoa().getNome();
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

}
