package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.util.List;

public class CalcularTotalInvestido {
    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;

    public CalcularTotalInvestido(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO, CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
        this.calcularTotalInvestidoPorAtivo = calcularTotalInvestidoPorAtivo;
    }


    public double calcularTotalInvestido(Carteira carteira) {
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();

            double totalInvestido = 0.0;

            if(ativos.isEmpty())
                return 0.0;

            for (Ativo ativo : ativos) {
                CalcularTotalInvestidoPorAtivo investimentoAtivo = new CalcularTotalInvestidoPorAtivo(carteiraDAO,ativoDAO);
                double calculado = investimentoAtivo.calcularTotalInvestidoPorAtivo(ativo);
                totalInvestido += calculado;
            }

            return totalInvestido;
        }

        return 0.0; // Retorna 0.0 caso não encontre a carteira
    }
}
