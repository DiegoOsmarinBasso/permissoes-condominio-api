package br.com.townsq.condominio.permissoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.townsq.condominio.permissoes.service.BaseUsuariosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1")
@Api(value = "CondominioPermissoes")
public class CondominioPermissoesController {

    @Autowired
    private BaseUsuariosService baseUsuarios;

    @GetMapping(path = "/permissoes", produces = "text/plain")
    @ApiOperation(value = "Retorna a lista de permissões mais altas que determinado usuário possui em cada condomínio.")
    public ResponseEntity<String> permissoesCondomino(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "arquivo_base", required = false) String arquivoBase) {

        return ResponseEntity.ok().body(baseUsuarios.obtemPermissoesUsuario(email, arquivoBase));
    }

}
