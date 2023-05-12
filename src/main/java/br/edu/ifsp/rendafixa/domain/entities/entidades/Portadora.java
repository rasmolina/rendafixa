package br.edu.ifsp.rendafixa.domain.entities.entidades;

public class Portadora {
    private String nome;
    private String descricao;
    private String sigla;

    public Portadora(String nome, String descricao, String sigla) {
        this.nome = nome;
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Emissora{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }
}
