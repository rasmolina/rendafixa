package br.edu.ifsp.rendafixa.domain.entities.transacao;

public enum TipoTransacao {
    COMPRA("Compra"),
    VENDA("Venda");

    private String tipo;

    TipoTransacao(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
