package br.edu.ifsp.rendafixa.domain.entities.ativos;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;

import java.time.LocalDate;

public class Ativo {
    private Integer id;
    private String nome;
    private boolean liquidezDiaria;
    private LocalDate dataVencimento;
    private CategoriaAtivo categoriaAtivo;
    private CategoriaRentabilidade categoriaRentabilidade;
    private Emissora emissora;
    private Portadora portadora;
    private Indexador indexador;
    private Double rentabilidade;

    public Ativo(Integer id, String nome, boolean liquidezDiaria, LocalDate dataVencimento, CategoriaAtivo categoriaAtivo, CategoriaRentabilidade categoriaRentabilidade, Emissora emissora, Portadora portadora, Indexador indexador, Double rentabilidade) {
        this.id = id;
        this.nome = nome;
        this.liquidezDiaria = liquidezDiaria;
        this.dataVencimento = dataVencimento;
        this.categoriaAtivo = categoriaAtivo;
        this.categoriaRentabilidade = categoriaRentabilidade;
        this.emissora = emissora;
        this.portadora = portadora;
        this.indexador = indexador;
        this.rentabilidade = rentabilidade;
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

    public boolean isLiquidezDiaria() {
        return liquidezDiaria;
    }

    public void setLiquidezDiaria(boolean liquidezDiaria) {
        this.liquidezDiaria = liquidezDiaria;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public CategoriaAtivo getCategoriaAtivo() {
        return categoriaAtivo;
    }

    public void setCategoriaAtivo(CategoriaAtivo categoriaAtivo) {
        this.categoriaAtivo = categoriaAtivo;
    }

    public CategoriaRentabilidade getCategoriaRentabilidade() {
        return categoriaRentabilidade;
    }

    public void setCategoriaRentabilidade(CategoriaRentabilidade categoriaRentabilidade) {
        this.categoriaRentabilidade = categoriaRentabilidade;
    }

    public Emissora getEmissora() {
        return emissora;
    }

    public void setEmissora(Emissora emissora) {
        this.emissora = emissora;
    }

    public Portadora getPortadora() {
        return portadora;
    }

    public void setPortadora(Portadora portadora) {
        this.portadora = portadora;
    }

    public Indexador getIndexador() {
        return indexador;
    }

    public void setIndexador(Indexador indexador) {
        this.indexador = indexador;
    }

    public Double getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(Double rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", liquidezDiaria=" + liquidezDiaria +
                ", dataVencimento=" + dataVencimento +
                ", categoriaAtivo=" + categoriaAtivo +
                ", categoriaRentabilidade=" + categoriaRentabilidade +
                ", emissora=" + emissora +
                ", portadora=" + portadora +
                ", indexador=" + indexador +
                ", rentabilidade=" + rentabilidade +
                '}';
    }
}
