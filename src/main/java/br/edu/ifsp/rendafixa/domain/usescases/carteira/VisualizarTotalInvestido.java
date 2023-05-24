package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;

import java.util.List;

public class VisualizarTotalInvestido {
    private List<Carteira> ativos;

    public VisualizarTotalInvestido() {

    }

    public double calcularTotalInvestido(){
        if(ativos.isEmpty())
            return 0.0;

        double valorTotalInvestido = 0.0;

        for (Carteira carteira : ativos){
            valorTotalInvestido += carteira.getValorTotalCompra();
        }
        return valorTotalInvestido;
    }
}
