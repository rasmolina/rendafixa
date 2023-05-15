package br.edu.ifsp.rendafixa.domain.entities.transacao;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;

public class Transacao {

    private Integer id;
    private Ativo ativo;
    private Carteira carteira;

    public Transacao(int id, Ativo ativo, Carteira carteira) {
        this.id = id;
        this.ativo = ativo;
        this.carteira = carteira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", ativo=" + ativo +
                ", carteira=" + carteira +
                '}';
    }
}
