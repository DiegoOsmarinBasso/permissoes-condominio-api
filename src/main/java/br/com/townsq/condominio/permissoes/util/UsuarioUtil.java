package br.com.townsq.condominio.permissoes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.townsq.condominio.permissoes.model.CondominioPermissoes;
import br.com.townsq.condominio.permissoes.model.FuncionalidadeEnum;
import br.com.townsq.condominio.permissoes.model.Grupo;
import br.com.townsq.condominio.permissoes.model.PermissaoEnum;
import br.com.townsq.condominio.permissoes.model.Usuario;

@Component
public class UsuarioUtil {

    /**
     * 
     * @param usuario
     * @return
     */
    public String permissoesMaisAltas(Usuario usuario) {

        List<CondominioPermissoes> condominioPermissoesLista = new ArrayList<>();

        // Obtém permissões mais altas
        for (Grupo grupo : usuario.getGrupos()) {

            boolean condominioEstaNaLista = false;

            // Verifica se condomínio já está na lista
            for (CondominioPermissoes condominioPermissoes : condominioPermissoesLista) {

                // Se já está na lista, computa as permissões mais altas
                if (condominioPermissoes.getCondominio().equals(grupo.getIdCondominio())) {

                    condominioEstaNaLista = true;
                    computaPermissoesMaisAltas(condominioPermissoes, grupo);
                }
            }

            // Se condomínio não etá na lista, insere novo registro
            if (!condominioEstaNaLista) {
                CondominioPermissoes condomioPermissoes = new CondominioPermissoes(grupo.getIdCondominio());
                condomioPermissoes.setPermissoes(grupo.getPermissoes());
                condominioPermissoesLista.add(condomioPermissoes);
            }

        }

        // Monta String de saída
        return montaStringSaida(condominioPermissoesLista);
    }

    /**
     * 
     * @param condominioPermissoes
     * @param grupo
     */
    private void computaPermissoesMaisAltas(CondominioPermissoes condominioPermissoes, Grupo grupo) {

        Map<FuncionalidadeEnum, PermissaoEnum> permissoesCondomino = condominioPermissoes.getPermissoes();
        Map<FuncionalidadeEnum, PermissaoEnum> permissoesGrupo = grupo.getPermissoes();

        for (FuncionalidadeEnum funcionalidade : permissoesCondomino.keySet()) {
            if (permissoesGrupo.get(funcionalidade).ordinal() < permissoesCondomino.get(funcionalidade).ordinal()) {
                permissoesCondomino.put(funcionalidade, permissoesGrupo.get(funcionalidade));
            }
        }
    }

    /**
     * 
     * @param condominioPermissoesLista
     * @return
     */
    private String montaStringSaida(List<CondominioPermissoes> condominioPermissoesLista) {

        String permissoesSaida = "";
        for (CondominioPermissoes condominioPermissoes : condominioPermissoesLista) {
            permissoesSaida += condominioPermissoes.toString() + "\n";
        }
        return permissoesSaida;
    }
}
