package br.com.townsq.condominio.permissoes.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.townsq.condominio.permissoes.handler.PermissaoErroException;
import br.com.townsq.condominio.permissoes.util.GrupoUtil;
import br.com.townsq.condominio.permissoes.util.UsuarioUtil;

@RunWith(SpringRunner.class)
public class BaseUsuariosServiceTest {

    @TestConfiguration
    static class BaseUsuariosServiceContextConfiguration {

        @Bean
        public BaseUsuariosService baseUsuariosService() {
            return new BaseUsuariosService();
        }
    }

    @Autowired
    private BaseUsuariosService baseUsuariosService;
    @MockBean
    private GrupoUtil grupoUtil;
    @MockBean
    private UsuarioUtil usuarioUtil;

    @Test(expected = PermissaoErroException.class)
    public void obtemPermissoesUsuarioArquivoNaoEncontradoTest() {
        baseUsuariosService.obtemPermissoesUsuario("email", "arquivo_nao_necontrado.txt");
    }

    @Test(expected = PermissaoErroException.class)
    public void obtemPermissoesUsuarioArquivoInvalidoTest() {
        baseUsuariosService.obtemPermissoesUsuario("email", "arquivo_invalido.txt");
    }

    @Test
    public void obtemPermissoesUsuarioOkTest() {

        Mockito.when(usuarioUtil.permissoesMaisAltas(any())).thenReturn("OK!");

        String retornoObtido = baseUsuariosService
                .obtemPermissoesUsuario("rodrigo.soares@gmail.com", "arquivo_teste.txt");

        assertEquals("OK!", retornoObtido);
    }
}
