package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizarComposicaoCarteira {

    //Retorna a composição absoluta ou relativa dos ativos presentes na carteira
    private List<Carteira> ativos;
    private VisualizarTotalInvestido visualizarTotalInvestido;

    public VisualizarComposicaoCarteira(List<Carteira> ativos) {
        this.ativos = ativos;
    }

    public void visualizarComposicaoCarteira() {
        double valorTotalInvestido = visualizarTotalInvestido.calcularTotalInvestido();
        if (valorTotalInvestido == 0.0) {
            System.out.println("Não há ativos na carteira!");
            return;
        }

        //Mapa valorInvestidoPorCategoria para armazenar o valor investido em cada categoria
        Map<CategoriaAtivo, Double> valorInvestidoPorCategoria = new HashMap<>();
        // percorre cada ativo da carteira e vai somando o valor investido em cada categoria
        for (Carteira carteira : ativos) {
            CategoriaAtivo categoriaAtivo = carteira.getAtivo().getCategoriaAtivo();
            //getOrDefault() é usado para obter o valor atual da categoria no mapa, caso já exista, ou null caso contrário.
            double valorInvestido = valorInvestidoPorCategoria.getOrDefault(categoriaAtivo, null);
            valorInvestido += carteira.getValorTotalCompra();
            valorInvestidoPorCategoria.put(categoriaAtivo, valorInvestido);
        }

        System.out.println("Composição da carteira:");
        //keySet() é um método da classe Map em Java que retorna um conjunto contendo as chaves presentes no mapa.
        for (CategoriaAtivo categoriaAtivo : valorInvestidoPorCategoria.keySet()) {
            double valorInvestido = valorInvestidoPorCategoria.get(categoriaAtivo);
            double composicaoAbsoluta = valorInvestido;
            double composicaoRelativa = (valorInvestido / valorTotalInvestido) * 100;

            System.out.println("Categoria: " + categoriaAtivo);
            System.out.println("Valor Investido: " + valorInvestido);
            System.out.println("Composição Absoluta: " + composicaoAbsoluta);
            System.out.println("Composição Relativa (%): " + composicaoRelativa);
            System.out.println();
        }
    }
}
