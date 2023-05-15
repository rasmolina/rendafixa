package br.edu.ifsp.rendafixa.domain.entities.indexadores;

public class Indexador {

    private Integer id;
    private SiglaIndexador sigla;
    private String nome;
    private Double valor;

    public Indexador(Integer id, SiglaIndexador sigla, String nome, Double valor) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }
    public SiglaIndexador getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
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
                "id=" + id +
                ", sigla=" + sigla +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}
