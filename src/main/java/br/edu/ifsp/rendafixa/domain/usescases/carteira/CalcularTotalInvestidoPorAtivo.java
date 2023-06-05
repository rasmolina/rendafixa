package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;

import java.util.List;

public class CalcularTotalInvestidoPorAtivo {

    public double calcularTotalInvestidoPorAtivo(Ativo ativo) {
        List<ItemAtivo> aplicacoes = ativo.getItensAtivo();
        double valorTotalAplicado = 0.0;

        if(!aplicacoes.isEmpty()){
            for(ItemAtivo aplicacao : aplicacoes){
                double valorAplicado = aplicacao.getValorDaCompra();
                valorTotalAplicado += valorAplicado;
            }
        }
        return valorTotalAplicado;
    }
}
