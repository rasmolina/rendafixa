package br.edu.ifsp.rendafixa.domain.entities.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;

import java.time.LocalDate;

public class Carteira {

    private Ativo ativo;
    private Double valorTotalCompra;
    private LocalDate dataCompra;
    private LocalDate dataVencimento;

    public Carteira(Ativo ativo, Double valorTotalCompra, LocalDate dataVencimento) {
        this.ativo = ativo;
        this.valorTotalCompra = valorTotalCompra;
        this.dataCompra = LocalDate.now();
        this.dataVencimento = dataVencimento;
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

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "ativo=" + ativo +
                ", valorTotalCompra=" + valorTotalCompra +
                ", dataCompra=" + dataCompra +
                ", dataVencimento=" + dataVencimento +
                '}';
    }
}
