package br.edu.ifsp.rendafixa.domain.entities.transacao;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;

import java.time.LocalDate;

public class Transacao {
    private Integer id;
    private LocalDate dataTransacao;
    private LocalDate dataVenda;
    private LocalDate dataCompra;
    private Ativo ativo;
    private double valorTransacao;
    private TipoTransacao tipo;

    //Construtor para transação de compra
    public Transacao(LocalDate dataTransacao, LocalDate dataCompra, Ativo ativo, double valorTransacao, TipoTransacao tipo) {
        this.dataTransacao = dataTransacao;
        this.dataCompra = dataCompra;
        this.ativo = ativo;
        this.valorTransacao = valorTransacao;
        this.tipo = tipo;
    }

    //Construtor para transação de venda
    public Transacao(LocalDate dataTransacao, LocalDate dataVenda, LocalDate dataCompra, Ativo ativo, double valorTransacao, TipoTransacao tipo) {
        this.dataTransacao = dataTransacao;
        this.dataVenda = dataVenda;
        this.dataCompra = dataCompra;
        this.ativo = ativo;
        this.valorTransacao = valorTransacao;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", dataTransacao=" + dataTransacao +
                ", dataVenda=" + dataVenda +
                ", dataCompra=" + dataCompra +
                ", ativo=" + ativo +
                ", valorTransacao=" + valorTransacao +
                ", tipo=" + tipo +
                '}';
    }
}
