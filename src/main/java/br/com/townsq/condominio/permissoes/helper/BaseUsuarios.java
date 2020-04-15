package br.com.townsq.condominio.permissoes.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.townsq.condominio.permissoes.handler.PermissaoErroEnum;
import br.com.townsq.condominio.permissoes.handler.PermissaoErroException;
import br.com.townsq.condominio.permissoes.model.Grupo;
import br.com.townsq.condominio.permissoes.model.Usuario;

@Component
public class BaseUsuarios {

    private static final String DIRETORIO_PADRAO = "./arquivos/";
    private static final String ARQUIVO_PADRAO = "base_usuarios.txt";
    private static final String USUARIO = "Usuario";
    private static final String GRUPO = "Grupo";

    /**
     * 
     * @param email
     * @param arquivo
     * @return
     */
    public Usuario obtemPermissoesUsuario(String email, String arquivo) {

        arquivo = arquivo == null ? ARQUIVO_PADRAO : arquivo;
        Usuario usuario = null;
        List<Grupo> grupos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DIRETORIO_PADRAO + arquivo))) {

            while (br.ready()) {

                String[] campos = br.readLine().split(";");

                switch (campos[0]) {
                case USUARIO:
                    if (usuario == null && email.equals(campos[1])) {
                        usuario = new Usuario(email);
                    }
                    break;
                case GRUPO:
                    // TODO: funcao cria grupo
                    grupos.add(new Grupo(campos[1], campos[2]));
                    break;
                default:
                    throw new PermissaoErroException(PermissaoErroEnum.ARQUIVO_INVALIDO);
                }
            }

            // TODO: funcao filtra grupos
            if (usuario != null) {
                usuario.setGrupos(null);
                return usuario;
            } else {
                throw new PermissaoErroException(PermissaoErroEnum.USUARIO_NAO_ENCONTRADO);
            }

        } catch (IOException e) {

            throw new PermissaoErroException(PermissaoErroEnum.ARQUIVO_VAZIO_NAO_ENCONTRADO);
        }
    }
}
