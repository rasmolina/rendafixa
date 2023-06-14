package br.edu.ifsp.rendafixa.domain.entities.indexadores;

import java.util.Arrays;

public enum SiglaIndexador {
    IPCA("Índice Nacional de Preços ao Consumidor Amplo"),
    IGPM("Índice Global de Preços do Mercado"),
    SELIC("Sistema Especial de Liquidação e Custódia"),
    DI("Depósito Interbancário");

    private String sigla;

    SiglaIndexador(String sigla){
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return sigla;
    }

    public static SiglaIndexador toEnum(String value){
        return Arrays.stream(SiglaIndexador.values())
                .filter(c->value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }


}
