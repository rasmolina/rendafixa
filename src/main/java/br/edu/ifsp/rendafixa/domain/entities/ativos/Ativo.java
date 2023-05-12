package br.edu.ifsp.rendafixa.domain.entities.ativos;

import br.edu.ifsp.rendafixa.domain.entities.entidades.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.entidades.Portadora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;

import java.time.LocalDate;

public class Ativo {
    private String nome;
    private boolean liquidezDiaria;
    private CategoriaAtivo categoriaAtivo;
    private CategoriaRentabilidade categoriaRentabilidade;
    private Emissora emissora;
    private Portadora portadora;
    private Indexador indexador;
    private Double rentabilidade;

    public Ativo(String nome, boolean liquidezDiaria, CategoriaAtivo categoriaAtivo, CategoriaRentabilidade categoriaRentabilidade, Emissora emissora, Portadora portadora, Indexador indexador, Double rentabilidade) {
        this.nome = nome;
        this.liquidezDiaria = liquidezDiaria;
        this.categoriaAtivo = categoriaAtivo;
        this.categoriaRentabilidade = categoriaRentabilidade;
        this.emissora = emissora;
        this.portadora = portadora;
        this.indexador = indexador;
        this.rentabilidade = rentabilidade;
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
                "nome='" + nome + '\'' +
                ", liquidezDiaria=" + liquidezDiaria +
                ", categoriaAtivo=" + categoriaAtivo +
                ", categoriaRentabilidade=" + categoriaRentabilidade +
                ", emissora=" + emissora +
                ", portadora=" + portadora +
                ", indexador=" + indexador +
                ", rentabilidade=" + rentabilidade +
                '}';
    }
}
