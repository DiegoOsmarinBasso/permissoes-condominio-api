package br.com.townsq.condominio.permissoes.util;

import org.springframework.stereotype.Component;

import br.com.townsq.condominio.permissoes.model.FuncionalidadeEnum;
import br.com.townsq.condominio.permissoes.model.Grupo;
import br.com.townsq.condominio.permissoes.model.PermissaoEnum;

@Component
public class GrupoUtil {

    /**
     * 
     * @param campos
     * @return
     */
    public Grupo geraGrupo(String[] campos) {

        Grupo grupo = new Grupo(campos[1], campos[2]);
        String permissoes[] = campos[3].replaceAll("\\[|\\]|\\(|\\)", "").split(",");

        for (int i = 0; i < permissoes.length; i += 2) {
            grupo.getPermissoes().put(
                    FuncionalidadeEnum.valueOf(permissoes[i]), PermissaoEnum.valueOf(permissoes[i + 1]));
        }

        return grupo;
    }

}
