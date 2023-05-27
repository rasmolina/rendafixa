package br.edu.ifsp.rendafixa.domain.entities.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Carteira {

    private Integer id;
    private List<Ativo> ativos;

    public Carteira(Integer id, List<Ativo> ativos) {
        this.id = id;
        this.ativos = new ArrayList<>();
    }

    public Carteira(){}

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

    @Override
    public String toString() {
        return "Carteira{" +
                "id=" + id +
                ", ativos=" + ativos +
                '}';
    }
}
