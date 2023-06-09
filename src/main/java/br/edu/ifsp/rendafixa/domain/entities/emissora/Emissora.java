package br.edu.ifsp.rendafixa.domain.entities.emissora;

public class Emissora {

    private Integer id;
    private String nome;
    private String descricao;
    private String sigla;

    public Emissora() {
    }

    public Emissora(Integer id){
        this.id = id;
    }

    public Emissora(Integer id, String nome, String descricao, String sigla) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return nome;
        /*return "Emissora{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", sigla='" + sigla + '\'' +
                '}';
    }*/
    }
}
