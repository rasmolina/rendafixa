package br.edu.ifsp.rendafixa.domain.entities.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;

import java.time.LocalDate;

public class Carteira {

    private Integer id;
    private Ativo ativo;
    private Double valorTotalCompra;
    private LocalDate dataCompra;

    public Carteira(Integer id, Ativo ativo, Double valorTotalCompra, LocalDate dataCompra) {
        this.id = id;
        this.ativo = ativo;
        this.valorTotalCompra = valorTotalCompra;
        this.dataCompra = dataCompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    @Override
    public String toString() {
        return "Carteira{" +
                "id=" + id +
                ", ativo=" + ativo +
                ", valorTotalCompra=" + valorTotalCompra +
                ", dataCompra=" + dataCompra +
                '}';
    }
}
