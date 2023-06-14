package br.edu.ifsp.rendafixa.domain.entities.ativos;

import java.util.Arrays;

public enum CategoriaAtivo {
    TD("Tesouro Direto"),
    LCI("Letra de Crédito Imobiliário"),
    LCA("Letra de Crédito de Agronegócio"),
    CDB("Certificado de Depósito Bancário"),
    CDI("Certificado de Depósito Interbancário"),
    RDB("Recibo de Depósito Bancário"),
    DEB("Debênture"),
    PP("Poupança");

    private String categoria;

    CategoriaAtivo(String categoria){
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }

    public static CategoriaAtivo toEnum(String value){
        return Arrays.stream(CategoriaAtivo.values())
                .filter(c->value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

}
