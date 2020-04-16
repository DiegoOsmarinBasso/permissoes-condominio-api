package br.com.townsq.condominio.permissoes.model;

import java.util.HashMap;
import java.util.Map;

public class CondominioPermissoes {

    private String condominio;
    private Map<FuncionalidadeEnum, PermissaoEnum> permissoes;

    public CondominioPermissoes(String condominio) {
        this.condominio = condominio;
        this.permissoes = new HashMap<>();
    }

    public String getCondominio() {
        return condominio;
    }

    public void setCondominio(String condominio) {
        this.condominio = condominio;
    }

    public Map<FuncionalidadeEnum, PermissaoEnum> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Map<FuncionalidadeEnum, PermissaoEnum> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public String toString() {

        String saida = condominio + ";[";
        for (FuncionalidadeEnum funcionalidade : permissoes.keySet()) {
            saida += "(" + funcionalidade.name() + "," + permissoes.get(funcionalidade).name() + "),";
        }
        return saida.substring(0, saida.length() - 1) + "]";
    }
}
