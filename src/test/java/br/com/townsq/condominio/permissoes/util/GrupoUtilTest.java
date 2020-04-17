package br.com.townsq.condominio.permissoes.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.townsq.condominio.permissoes.model.Grupo;
import br.com.townsq.condominio.permissoes.model.TipoGrupoEnum;

@RunWith(SpringRunner.class)
public class GrupoUtilTest {

    @TestConfiguration
    static class GrupoUtilContextConfiguration {

        @Bean
        public GrupoUtil grupoUtil() {
            return new GrupoUtil();
        }
    }

    @Autowired
    private GrupoUtil grupoUtil;

    @Test
    public void geraGruposUsuarioOkTest() {

        String[] campos = "Usuario;joao.costa@gmail.com;[(Morador,1),(Sindico,1),(Sindico,2)]".split(";");

        List<Grupo> grupos = grupoUtil.geraGruposUsuario(campos);
        assertEquals(TipoGrupoEnum.Morador, grupos.get(0).getTipoGrupo());
        assertEquals("1", grupos.get(0).getIdCondominio());
        assertEquals(TipoGrupoEnum.Sindico, grupos.get(1).getTipoGrupo());
        assertEquals("1", grupos.get(1).getIdCondominio());
        assertEquals(TipoGrupoEnum.Sindico, grupos.get(2).getTipoGrupo());
        assertEquals("2", grupos.get(2).getIdCondominio());
    }

    @Test(expected = Exception.class)
    public void geraGruposUsuarioNokTest() {

        String[] campos = "".split(";");

        grupoUtil.geraGruposUsuario(campos);
    }

    @Test
    public void geraGrupoOkTest() {

        String[] campos = "Grupo;Morador;1;[(Reservas,Escrita),(Entregas,Nenhuma),(Usuarios,Leitura)]".split(";");

        Grupo grupo = grupoUtil.geraGrupo(campos);
        assertEquals(TipoGrupoEnum.Morador, grupo.getTipoGrupo());
        assertEquals("1", grupo.getIdCondominio());
        assertEquals(3, grupo.getPermissoes().size());
    }

    @Test(expected = Exception.class)
    public void geraGrupoNokTest() {

        String[] campos = "".split(";");

        grupoUtil.geraGrupo(campos);
    }

}
