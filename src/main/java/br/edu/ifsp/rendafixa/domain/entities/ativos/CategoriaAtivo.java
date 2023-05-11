package br.edu.ifsp.rendafixa.domain.entities.ativos;

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


}
