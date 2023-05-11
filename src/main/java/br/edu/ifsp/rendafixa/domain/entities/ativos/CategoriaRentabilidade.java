package br.edu.ifsp.rendafixa.domain.entities.ativos;

public enum CategoriaRentabilidade {

    PRÉ_FIXADO("Pré fixado"),
    POS_FIXADO("Pós fixado");
    }

    private String categoria;


    CategoriaRentabilidade(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "CategoriaRentabilidade{" +
                "categoria='" + categoria + '\'' +
                '}';
    }
}
