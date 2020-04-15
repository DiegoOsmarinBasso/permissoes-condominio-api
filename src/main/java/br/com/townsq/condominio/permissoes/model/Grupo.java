package br.com.townsq.condominio.permissoes.model;

import java.util.HashMap;
import java.util.Map;

public class Grupo {

    private TipoGrupoEnum tipoGrupo;
    private String idCondominio;
    private Map<FuncionalidadeEnum, PermissaoEnum> permissoes;

    public Grupo(String tipoGrupo, String idCondominio) {
        this.tipoGrupo = TipoGrupoEnum.valueOf(tipoGrupo);
        this.idCondominio = idCondominio;
        permissoes = new HashMap<>();
    }

    public TipoGrupoEnum getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(TipoGrupoEnum tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public String getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(String idCondominio) {
        this.idCondominio = idCondominio;
    }

    public Map<FuncionalidadeEnum, PermissaoEnum> getPermissoes() {
        return permissoes;
    }
}
