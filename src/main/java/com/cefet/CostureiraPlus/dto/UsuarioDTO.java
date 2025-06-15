package com.cefet.CostureiraPlus.dto;

import com.cefet.CostureiraPlus.entities.Pessoa;
import com.cefet.CostureiraPlus.entities.Usuario;

public class UsuarioDTO {

    private long id;
    private String login;
    private String senha;
    private String tipo;
    private Pessoa pessoa;

    public UsuarioDTO() {
        
    }

    public UsuarioDTO (Usuario usuario){
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.tipo = usuario.getTipo();
        this.pessoa = usuario.getPessoa();
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

    public Pessoa getPessoa() {
        return pessoa;
    }    

}
