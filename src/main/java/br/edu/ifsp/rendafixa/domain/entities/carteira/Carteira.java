package br.edu.ifsp.rendafixa.domain.entities.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;

import java.util.ArrayList;
import java.util.List;

public class Carteira {

    private Integer id;
    private List<Ativo> ativos;
    private Double valorDisponivelSaque;

    public Carteira(List<Ativo> ativos) {
        this.id = id;
        this.ativos = new ArrayList<>();
        this.valorDisponivelSaque = 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Ativo> getAtivos() {
        return ativos;
    }

    public void setAtivos(List<Ativo> ativos) {
        this.ativos = ativos;
    }

    public Double getValorDisponivelSaque() {
        return valorDisponivelSaque;
    }

    public void setValorDisponivelSaque(Double valorDisponivelSaque) {
        this.valorDisponivelSaque = valorDisponivelSaque;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "id=" + id +
                ", ativos=" + ativos +
                ", valorDisponivelSaque=" + valorDisponivelSaque +
                '}';
    }
}
