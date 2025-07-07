package com.cefet.CostureiraPlus.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acesso", nullable = false)
    private NivelAcesso nivelAcesso;

    public Usuario() {

    }

    public Usuario(long id, String login, String senha, String tipo, Pessoa pessoa, NivelAcesso nivelAcesso) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        this.pessoa = pessoa;
        this.nivelAcesso = nivelAcesso;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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

    public NivelAcesso getNivelAcesso() {
        // TODO Auto-generated method stub
        return nivelAcesso;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso){
        this.nivelAcesso = nivelAcesso;
    }

}
