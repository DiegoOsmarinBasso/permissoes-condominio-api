package br.com.townsq.condominio.permissoes.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.townsq.condominio.permissoes.handler.PermissaoErroEnum;
import br.com.townsq.condominio.permissoes.handler.PermissaoErroException;
import br.com.townsq.condominio.permissoes.model.Grupo;
import br.com.townsq.condominio.permissoes.model.Usuario;
import br.com.townsq.condominio.permissoes.util.GrupoUtil;
import br.com.townsq.condominio.permissoes.util.UsuarioUtil;

@Service
public class BaseUsuariosService {

    private static final String DIRETORIO_PADRAO = "./arquivos/";
    private static final String ARQUIVO_PADRAO = "base_usuarios.txt";
    private static final String USUARIO = "Usuario";
    private static final String GRUPO = "Grupo";

    @Autowired
    private GrupoUtil grupoUtil;
    @Autowired
    private UsuarioUtil usuarioUtil;

    /**
     * 
     * @param email
     * @param arquivo
     * @return
     */
    public String obtemPermissoesUsuario(String email, String arquivo) {

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
                    grupos.add(grupoUtil.geraGrupo(campos));
                    break;
                default:
                    throw new PermissaoErroException(PermissaoErroEnum.ARQUIVO_INVALIDO);
                }
            }
        } catch (IOException e) {
            throw new PermissaoErroException(PermissaoErroEnum.ARQUIVO_VAZIO_NAO_ENCONTRADO);
        } catch (Exception e) {
            throw new PermissaoErroException(PermissaoErroEnum.ARQUIVO_INVALIDO);
        }

        // TODO: funcao filtra grupos
        if (usuario != null) {
            usuario.setGrupos(grupos);
            return usuarioUtil.permissoesMaisAltas(usuario);
        } else {
            throw new PermissaoErroException(PermissaoErroEnum.USUARIO_NAO_ENCONTRADO);
        }
    }
}
