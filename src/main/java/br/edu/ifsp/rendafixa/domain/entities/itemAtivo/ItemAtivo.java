package br.edu.ifsp.rendafixa.domain.entities.itemAtivo;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;

import java.time.LocalDate;

public class ItemAtivo {
    private Integer id;
    private Ativo ativo;
    private LocalDate dataDaCompra;
    private Double valorDaCompra;

    public ItemAtivo(Ativo ativo, LocalDate dataDaCompra, Double valorDaCompra) {
        this.ativo = ativo;
        this.dataDaCompra = dataDaCompra;
        this.valorDaCompra = valorDaCompra;
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

    public LocalDate getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(LocalDate dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public Double getValorDaCompra() {
        return valorDaCompra;
    }

    public void setValorDaCompra(Double valorDaCompra) {
        this.valorDaCompra = valorDaCompra;
    }

    @Override
    public String toString() {
        return "ItemAtivo{" +
                "id=" + id +
                ", ativo=" + ativo +
                ", dataDaCompra=" + dataDaCompra +
                ", valorDaCompra=" + valorDaCompra +
                '}';
    }
}
