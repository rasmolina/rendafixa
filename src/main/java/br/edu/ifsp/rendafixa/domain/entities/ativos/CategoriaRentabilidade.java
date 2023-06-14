package br.edu.ifsp.rendafixa.domain.entities.ativos;

import java.util.Arrays;

public enum CategoriaRentabilidade {

    PRE_FIXADO("Pré fixado"),
    POS_FIXADO("Pós fixado");

    private String categoria;

    CategoriaRentabilidade(String categoria) {
        this.categoria = categoria;
    }

    /*
    @Override
    public String toString() {
        return "CategoriaRentabilidade{" +
                "categoria='" + categoria + '\'' +
                '}';
    }
    */

    @Override
    public String toString() {
        return categoria;
    }
    public static CategoriaRentabilidade toEnum(String value){
        return Arrays.stream(CategoriaRentabilidade.values())
                .filter(c->value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
