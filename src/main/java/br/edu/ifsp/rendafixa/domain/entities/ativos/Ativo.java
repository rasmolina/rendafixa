package br.edu.ifsp.rendafixa.domain.entities.ativos;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
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
    private double rentabilidade;
    private List<LocalDate> dataDaCompra;
    private List<Double> valorTotalDaCompra;

    //Construtor para ativo pós-fixado sem liquidez diária
    public Ativo(Integer id, String nome, LocalDate dataVencimento, CategoriaAtivo categoriaAtivo, Emissora emissora, Portadora portadora, Indexador indexador, CategoriaRentabilidade categoriaRentabilidade, double porcentagemSobreIndexador, double rentabilidade) {
        this.id = id;
        this.nome = nome;
        this.dataVencimento = dataVencimento;
        this.categoriaAtivo = categoriaAtivo;
        this.emissora = emissora;
        this.portadora = portadora;
        this.indexador = indexador;
        this.categoriaRentabilidade = categoriaRentabilidade;
        this.porcentagemSobreIndexador = porcentagemSobreIndexador;
        this.rentabilidade = rentabilidade;
        this.dataDaCompra = new ArrayList<>();
        this.valorTotalDaCompra = new ArrayList<>();
    }

    //Construtor para ativo pré-fixado sem liquidez diária
    public Ativo(Integer id, String nome, LocalDate dataVencimento, CategoriaAtivo categoriaAtivo, Emissora emissora, Portadora portadora, CategoriaRentabilidade categoriaRentabilidade, double rentabilidade) {
        this.id = id;
        this.nome = nome;
        this.liquidezDiaria = liquidezDiaria;
        this.dataVencimento = dataVencimento;
        this.categoriaAtivo = categoriaAtivo;
        this.emissora = emissora;
        this.portadora = portadora;
        this.categoriaRentabilidade = categoriaRentabilidade;
        this.rentabilidade = rentabilidade;
        this.dataDaCompra = new ArrayList<>();
        this.valorTotalDaCompra = new ArrayList<>();
    }

    //Construtor para ativo pré-fixado com liquidez diária
    public Ativo(Integer id, String nome, boolean liquidezDiaria, CategoriaAtivo categoriaAtivo, Emissora emissora, Portadora portadora, CategoriaRentabilidade categoriaRentabilidade, double rentabilidade) {
        this.id = id;
        this.nome = nome;
        this.liquidezDiaria = true;
        this.categoriaAtivo = categoriaAtivo;
        this.emissora = emissora;
        this.portadora = portadora;
        this.categoriaRentabilidade = categoriaRentabilidade;
        this.rentabilidade = rentabilidade;
        this.dataDaCompra = new ArrayList<>();
        this.valorTotalDaCompra = new ArrayList<>();
        this.dataVencimento = LocalDate.now();
    }

    //Construtor para ativo pós-fixado com liquidez diária
    public Ativo(Integer id, String nome, boolean liquidezDiaria, CategoriaAtivo categoriaAtivo, Emissora emissora, Portadora portadora, Indexador indexador, CategoriaRentabilidade categoriaRentabilidade, double porcentagemSobreIndexador, double rentabilidade) {
        this.id = id;
        this.nome = nome;
        this.liquidezDiaria = true;
        this.categoriaAtivo = categoriaAtivo;
        this.emissora = emissora;
        this.portadora = portadora;
        this.indexador = indexador;
        this.categoriaRentabilidade = categoriaRentabilidade;
        this.porcentagemSobreIndexador = porcentagemSobreIndexador;
        this.rentabilidade = rentabilidade;
        this.dataDaCompra = new ArrayList<>();
        this.valorTotalDaCompra = new ArrayList<>();
        this.dataVencimento = LocalDate.now();
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

    public List<LocalDate> getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(List<LocalDate> dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    public List<Double> getValorTotalDaCompra() {
        return valorTotalDaCompra;
    }

    public void setValorTotalDaCompra(List<Double> valorTotalDaCompra) {
        this.valorTotalDaCompra = valorTotalDaCompra;
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
