package br.com.townsq.condominio.permissoes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.townsq.condominio.permissoes.model.CondominioPermissoes;
import br.com.townsq.condominio.permissoes.model.Grupo;
import br.com.townsq.condominio.permissoes.model.Usuario;

@Component
public class UsuarioUtil {

    @Autowired
    private PermissaoUtil permissaoUtil;
    @Autowired
    private SaidaUtil saidaUtil;

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
                    permissaoUtil.computaPermissoesMaisAltas(condominioPermissoes, grupo);
                }
            }

            // Se condomínio não está na lista, insere novo registro
            if (!condominioEstaNaLista) {

                CondominioPermissoes condomioPermissoes = new CondominioPermissoes(grupo.getIdCondominio());
                condomioPermissoes.setPermissoes(grupo.getPermissoes());
                condominioPermissoesLista.add(condomioPermissoes);
            }
        }

        // Monta String de saída
        return saidaUtil.montaStringSaida(condominioPermissoesLista);
    }

    /**
     * 
     * @param usuario
     * @param listaGeralGrupos
     */
    public void populaGrupos(Usuario usuario, List<Grupo> listaGeralGrupos) {

        for (Grupo grupoUsuario : usuario.getGrupos()) {
            Optional<Grupo> grupo = listaGeralGrupos.stream().filter(g -> g.mesmoGrupo(grupoUsuario)).findAny();

            if (grupo != null) {
                grupoUsuario.setPermissoes(grupo.get().getPermissoes());
            }
        }
    }
}
