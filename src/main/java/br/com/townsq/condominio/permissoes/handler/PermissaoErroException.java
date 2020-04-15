package br.com.townsq.condominio.permissoes.handler;

public class PermissaoErroException extends RuntimeException {

    private static final long serialVersionUID = 3634767179525654890L;
    private PermissaoErroEnum permissaoErroEnum;

    public PermissaoErroException(PermissaoErroEnum permissaoErroEnum) {
        this.permissaoErroEnum = permissaoErroEnum;
    }

    public PermissaoErroEnum getPermissaoErroEnum() {
        return permissaoErroEnum;
    }
}
