package br.com.townsq.condominio.permissoes.model;

import java.util.List;

public class Usuario {

    private String email;
    private List<Grupo> grupos;

    public Usuario(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
