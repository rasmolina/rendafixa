package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.util.List;

public class ConsultarAtivoCarteira {
    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;

    public ConsultarAtivoCarteira(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO, CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
        this.calcularTotalInvestidoPorAtivo = calcularTotalInvestidoPorAtivo;
    }

    public void consultarAtivoNaCarteira(Carteira carteira, Ativo ativo) {
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            if (ativos.contains(ativo)) {
                CalcularTotalInvestidoPorAtivo investimentoAtivo = new CalcularTotalInvestidoPorAtivo(carteiraDAO,ativoDAO);
                double totalInvestido = investimentoAtivo.calcularTotalInvestidoPorAtivo(ativo);
                System.out.println("Ativo: " + ativo.getNome());
                System.out.println("Categoria: " + ativo.getCategoriaAtivo());
                System.out.printf("Total investido: %.2f\n", totalInvestido);
            } else {
                System.out.println("O ativo buscado não está presente na carteira!");
            }
        }
    }



}
