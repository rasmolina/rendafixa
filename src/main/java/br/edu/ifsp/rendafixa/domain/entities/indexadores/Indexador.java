package br.edu.ifsp.rendafixa.domain.entities.indexadores;

public class Indexador {

    private String nome;
    private String sigla;
    private String descricao;

    public Indexador(){}

    public Indexador(String nome, String sigla, String descricao) {
        this.nome = nome;
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Indexador{" +
                "Nome='" + nome + '\'' +
                ", Sigla='" + sigla + '\'' +
                ", Descrição='" + descricao + '\'' +
                '}';
    }
}
