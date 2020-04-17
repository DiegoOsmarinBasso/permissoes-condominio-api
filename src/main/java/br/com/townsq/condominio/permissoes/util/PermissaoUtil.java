package br.com.townsq.condominio.permissoes.util;

import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.townsq.condominio.permissoes.model.CondominioPermissoes;
import br.com.townsq.condominio.permissoes.model.FuncionalidadeEnum;
import br.com.townsq.condominio.permissoes.model.Grupo;
import br.com.townsq.condominio.permissoes.model.PermissaoEnum;

@Component
public class PermissaoUtil {

    /**
     * 
     * @param condominioPermissoes
     * @param grupo
     */
    public void computaPermissoesMaisAltas(CondominioPermissoes condominioPermissoes, Grupo grupo) {

        Map<FuncionalidadeEnum, PermissaoEnum> permissoesCondomino = condominioPermissoes.getPermissoes();
        Map<FuncionalidadeEnum, PermissaoEnum> permissoesGrupo = grupo.getPermissoes();

        for (FuncionalidadeEnum funcionalidade : permissoesCondomino.keySet()) {
            if (permissoesGrupo.get(funcionalidade).ordinal() < permissoesCondomino.get(funcionalidade).ordinal()) {
                permissoesCondomino.put(funcionalidade, permissoesGrupo.get(funcionalidade));
            }
        }
    }
}
