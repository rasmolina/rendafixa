package br.edu.ifsp.rendafixa.domain.entities.ativos;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ativo {
    private Integer id;
    private String nome;
    private boolean liquidezDiaria;
    private LocalDate dataVencimento;
    private CategoriaAtivo categoriaAtivo;
    private Emissora emissora;
    private Portadora portadora;
    private Indexador indexador;
    private CategoriaRentabilidade categoriaRentabilidade;
    private double porcentagemSobreIndexador;
    private double rentabilidade; //ao ano
    private List<ItemAtivo> itensAtivo = new ArrayList<>();

    public Ativo(Integer id){
        this.id = id;
    }

    public Ativo(){}

    public Ativo(Integer id, String nome, boolean liquidezDiaria, LocalDate dataVencimento, CategoriaAtivo categoriaAtivo, Emissora emissora, Portadora portadora, Indexador indexador, CategoriaRentabilidade categoriaRentabilidade, double porcentagemSobreIndexador, double rentabilidade) {
        this.id = id;
        this.nome = nome;
        this.liquidezDiaria = liquidezDiaria;
        this.dataVencimento = dataVencimento;
        this.categoriaAtivo = categoriaAtivo;
        this.emissora = emissora;
        this.portadora = portadora;
        this.indexador = indexador;
        this.categoriaRentabilidade = categoriaRentabilidade;
        this.porcentagemSobreIndexador = porcentagemSobreIndexador;
        this.rentabilidade = rentabilidade;
        this.itensAtivo = new ArrayList<>();
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

    public CategoriaRentabilidade getCategoriaRentabilidade() {
        return categoriaRentabilidade;
    }

    public void setCategoriaRentabilidade(CategoriaRentabilidade categoriaRentabilidade) {
        this.categoriaRentabilidade = categoriaRentabilidade;
    }

    public double getPorcentagemSobreIndexador() {
        return porcentagemSobreIndexador;
    }

    public void setPorcentagemSobreIndexador(double porcentagemSobreIndexador) {
        this.porcentagemSobreIndexador = porcentagemSobreIndexador;
    }

    public double getRentabilidade() {
        return rentabilidade;
    }

    public void setRentabilidade(double rentabilidade) {
        this.rentabilidade = rentabilidade;
    }

    public List<ItemAtivo> getItensAtivo() {
        return itensAtivo;
    }

    public void setItensAtivo(List<ItemAtivo> itensAtivo) {
        this.itensAtivo = itensAtivo;
    }

    @Override
    public String toString() {
        return "Ativo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", liquidezDiaria=" + liquidezDiaria +
                ", dataVencimento=" + dataVencimento +
                ", categoriaAtivo=" + categoriaAtivo +
                ", emissora=" + emissora +
                ", portadora=" + portadora +
                ", indexador=" + indexador +
                ", categoriaRentabilidade=" + categoriaRentabilidade +
                ", porcentagemSobreIndexador=" + porcentagemSobreIndexador +
                ", rentabilidade=" + rentabilidade +
                '}';
    }
}
