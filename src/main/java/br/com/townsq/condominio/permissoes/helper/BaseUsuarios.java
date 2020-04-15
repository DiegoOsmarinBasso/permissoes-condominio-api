package br.com.townsq.condominio.permissoes.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;

import org.springframework.stereotype.Component;

@Component
public class BaseUsuarios {

    private static final String DIRETORIO_PADRAO = "./arquivos/";
    private static final String ARQUIVO_PADRAO = "base_usuarios.txt";

    /**
     * 
     * @param arquivo
     * @return
     */
    public String obtemBaseDeArquivo(String arquivo) {

        arquivo = arquivo == null ? ARQUIVO_PADRAO : arquivo;

        try (BufferedReader br = new BufferedReader(new FileReader(DIRETORIO_PADRAO + arquivo))) {

            return br.readLine();

        } catch (IOException e) {

            e.printStackTrace();
            throw new InputMismatchException(arquivo
                    + " não encontrado! Por favor informe um arquivo válido existente no diretório "
                    + DIRETORIO_PADRAO + "!");
        }
    }
}
