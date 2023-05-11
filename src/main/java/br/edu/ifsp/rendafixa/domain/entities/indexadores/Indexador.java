package br.edu.ifsp.rendafixa.domain.entities.indexadores;

public class Indexador {
    private SiglaIndexador sigla;
    private String nome;
    private Float valor;

    public Indexador(){}

    public Indexador(SiglaIndexador sigla, String nome, Float valor) {
        this.sigla = sigla;
        this.nome = nome;
        this.valor = valor;
    }

    public SiglaIndexador getSigla() {
        return sigla;
    }

    public void setSigla(SiglaIndexador sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Indexador{" +
                "sigla=" + sigla +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}
