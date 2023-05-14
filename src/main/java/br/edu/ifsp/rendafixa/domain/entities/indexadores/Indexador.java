package br.edu.ifsp.rendafixa.domain.entities.indexadores;

public class Indexador {
    private SiglaIndexador sigla;
    private String nome;
    private Double valor;

    public Indexador(){}

    public Indexador(SiglaIndexador sigla, String nome, Double valor) {
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
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
