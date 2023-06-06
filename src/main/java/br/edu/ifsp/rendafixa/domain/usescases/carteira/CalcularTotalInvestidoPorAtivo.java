package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.util.List;

public class CalcularTotalInvestidoPorAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;

    public CalcularTotalInvestidoPorAtivo(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
    }

    public double calcularTotalInvestidoPorAtivo(Ativo ativo) {
        if (ativo == null)
            return 0.0;

        List<ItemAtivo> aplicacoes = ativo.getItensAtivo();

        if(aplicacoes == null || aplicacoes.isEmpty())
            return 0.0;

        double valorTotalAplicado = 0.0;
        for(ItemAtivo aplicacao : aplicacoes){
            double valorAplicado = aplicacao.getValorDaCompra();
            valorTotalAplicado += valorAplicado;
        }
        return valorTotalAplicado;
    }

}
