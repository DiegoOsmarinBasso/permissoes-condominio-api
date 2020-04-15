package br.com.townsq.condominio.permissoes.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class ResourceExceptionHandler {

    @ExceptionHandler(PermissaoErroException.class)
    public ResponseEntity<String> erroDeNegocio(PermissaoErroException excecao) {

        return ResponseEntity.status(excecao.getPermissaoErroEnum().getHttpStatus())
                .body(excecao.getPermissaoErroEnum().getMensagem());
    }

}
