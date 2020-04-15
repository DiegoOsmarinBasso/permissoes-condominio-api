package br.com.townsq.condominio.permissoes.handler;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.springframework.http.HttpStatus;

public enum PermissaoErroEnum {

    ARQUIVO_INVALIDO("Arquivo inválido! O arquivo não está no formato padrão.", NOT_ACCEPTABLE),
    ARQUIVO_VAZIO_NAO_ENCONTRADO("Arquivo vazio ou não encontrado! Por favor informe um arquivo válido.", NOT_FOUND),
    USUARIO_NAO_ENCONTRADO("Usuário não encontrado no arquivo base!", UNPROCESSABLE_ENTITY);

    private String mensagem;
    private HttpStatus httpStatus;

    private PermissaoErroEnum(String mensagem, HttpStatus httpStatus) {
        this.mensagem = mensagem;
        this.httpStatus = httpStatus;
    }

    public String getMensagem() {
        return mensagem;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
