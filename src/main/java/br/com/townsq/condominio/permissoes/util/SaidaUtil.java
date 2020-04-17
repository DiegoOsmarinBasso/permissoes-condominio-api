package br.com.townsq.condominio.permissoes.util;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.townsq.condominio.permissoes.model.CondominioPermissoes;

@Component
public class SaidaUtil {

    /**
     * 
     * @param condominioPermissoesLista
     * @return
     */
    public String montaStringSaida(List<CondominioPermissoes> condominioPermissoesLista) {

        String permissoesSaida = "";
        for (CondominioPermissoes condominioPermissoes : condominioPermissoesLista) {
            permissoesSaida += condominioPermissoes.toString() + "\n";
        }
        return permissoesSaida;
    }

}
