package br.com.townsq.condominio.permissoes.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.townsq.condominio.permissoes.model.CondominioPermissoes;
import br.com.townsq.condominio.permissoes.model.Grupo;
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

        for (Grupo grupo : usuario.getGrupos()) {

            CondominioPermissoes condomioPermissoes = new CondominioPermissoes(grupo.getIdCondominio());

            condomioPermissoes.setPermissoes(grupo.getPermissoes());

            condominioPermissoesLista.add(condomioPermissoes);
        }

        String permissoesSaida = "";

        for (CondominioPermissoes condomioPermissoes : condominioPermissoesLista) {
            permissoesSaida += condomioPermissoes.toString() + "\n";
        }

        return permissoesSaida;
    }

}
