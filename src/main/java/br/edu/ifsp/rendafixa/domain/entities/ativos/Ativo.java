package br.edu.ifsp.rendafixa.domain.entities.ativos;

import br.edu.ifsp.rendafixa.domain.entities.entidades.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.entidades.Portadora;

import java.time.LocalDate;

public class Ativo {
    private String nome;
    private boolean liquidezDiaria;
    private CategoriaAtivo categoriaAtivo;
    private CategoriaRentabilidade categoriaRentabilidade;
    private Emissora emissora;
    private Portadora portadora;

    public Ativo(String nome, boolean liquidezDiaria, CategoriaAtivo categoriaAtivo, CategoriaRentabilidade categoriaRentabilidade, Emissora emissora, Portadora portadora) {
        this.nome = nome;
        this.liquidezDiaria = liquidezDiaria;
        this.categoriaAtivo = categoriaAtivo;
        this.categoriaRentabilidade = categoriaRentabilidade;
        this.emissora = emissora;
        this.portadora = portadora;
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

    @Override
    public String toString() {
        return "Ativo{" +
                "nome='" + nome + '\'' +
                ", liquidezDiaria=" + liquidezDiaria +
                ", categoriaAtivo=" + categoriaAtivo +
                ", categoriaRentabilidade=" + categoriaRentabilidade +
                ", emissora=" + emissora +
                ", portadora=" + portadora +
                '}';
    }
}
