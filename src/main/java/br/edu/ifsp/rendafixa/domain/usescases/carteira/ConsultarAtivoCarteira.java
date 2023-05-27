package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;

import java.util.List;

public class ConsultarAtivoCarteira {
    private ConsultarCarteira consultarCarteira;
    private CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;

    public void consultarAtivoNaCarteira(Integer idCarteira, Ativo ativo) {
        Carteira carteira = consultarCarteira.buscarCarteiraPorId(idCarteira);
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            if (ativos.contains(ativo)) {
                double totalInvestido = calcularTotalInvestidoPorAtivo.calcularTotalInvestidoPorAtivo(ativo);
                System.out.println("Ativo: " + ativo.getNome());
                System.out.printf("Total investido: %.2f\n", totalInvestido);
            } else {
                System.out.println("O ativo buscado não está presente na carteira!");
            }
        }
    }



}
