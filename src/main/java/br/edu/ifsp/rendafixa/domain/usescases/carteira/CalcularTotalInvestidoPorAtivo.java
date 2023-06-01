package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;

import java.util.List;

public class CalcularTotalInvestidoPorAtivo {

    public double calcularTotalInvestidoPorAtivo(Ativo ativo) {
        List<Double> valoresCompra = ativo.getValorTotalDaCompra();
        double totalInvestido = 0.0;

        for (double valorCompra : valoresCompra) {
            totalInvestido += valorCompra;
        }

        return totalInvestido;
    }
}
