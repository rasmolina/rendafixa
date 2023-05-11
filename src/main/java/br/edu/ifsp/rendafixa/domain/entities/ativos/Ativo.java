package br.edu.ifsp.rendafixa.domain.entities.ativos;

import br.edu.ifsp.rendafixa.domain.entities.entidades.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.entidades.Portadora;

import java.time.LocalDate;

public class Ativo {

    private String nome;
    private LocalDate vencimento;
    private boolean liquidezDiaria;
    private CategoriaAtivo categoria;
    private Emissora emissora;
    private Portadora portadora;

    public Ativo(String nome, LocalDate vencimento, boolean liquidezDiaria, CategoriaAtivo categoria, Emissora emissora, Portadora portadora) {
        this.nome = nome;
        this.vencimento = vencimento;
        this.liquidezDiaria = liquidezDiaria;
        this.categoria = categoria;
        this.emissora = emissora;
        this.portadora = portadora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public boolean isLiquidezDiaria() {
        return liquidezDiaria;
    }

    public void setLiquidezDiaria(boolean liquidezDiaria) {
        this.liquidezDiaria = liquidezDiaria;
    }

    public CategoriaAtivo getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAtivo categoria) {
        this.categoria = categoria;
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
                ", vencimento=" + vencimento +
                ", liquidezDiaria=" + liquidezDiaria +
                ", categoria=" + categoria +
                ", emissora=" + emissora +
                ", portadora=" + portadora +
                '}';
    }
}
