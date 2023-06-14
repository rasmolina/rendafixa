package br.edu.ifsp.rendafixa.domain.entities.transacao;

import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;

import java.util.Arrays;

public enum TipoTransacao {
    COMPRA("Compra"),
    VENDA("Venda");

    private String tipo;

    TipoTransacao(String tipo) {
        this.tipo = tipo;
    }

    /*
    @Override
    public String toString() {
        return "Transacao{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
    */
    @Override
    public String toString() {
        return tipo;
    }

    public static TipoTransacao toEnum(String value){
        return Arrays.stream(TipoTransacao.values())
                .filter(c->value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
