package br.edu.ifsp.rendafixa.domain.entities.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;

import java.time.LocalDate;

public class Carteira {

    private Ativo ativo;
    private Double valorTotalCompra;
    private LocalDate dataCompra;

    public Carteira(Ativo ativo, Double valorTotalCompra) {
        this.ativo = ativo;
        this.valorTotalCompra = valorTotalCompra;
        this.dataCompra = LocalDate.now();
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public Double getValorTotalCompra() {
        return valorTotalCompra;
    }

    public void setValorTotalCompra(Double valorTotalCompra) {
        this.valorTotalCompra = valorTotalCompra;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "ativo=" + ativo +
                ", valorTotalCompra=" + valorTotalCompra +
                ", dataCompra=" + dataCompra +
                '}';
    }
}
