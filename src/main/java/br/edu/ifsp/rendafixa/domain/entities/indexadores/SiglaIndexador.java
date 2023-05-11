package br.edu.ifsp.rendafixa.domain.entities.indexadores;

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


}
