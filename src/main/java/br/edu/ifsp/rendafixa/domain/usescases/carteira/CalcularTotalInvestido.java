package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.util.List;

public class CalcularTotalInvestido {
    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;

    public CalcularTotalInvestido(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
    }

    public double calcularTotalInvestido(Carteira carteira) {
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            double totalInvestido = 0.0;

            for (Ativo ativo : ativos) {
                double investimentoAtivo = calcularTotalInvestidoPorAtivo.calcularTotalInvestidoPorAtivo(ativo);
                totalInvestido += investimentoAtivo;
            }

            return totalInvestido;
        }

        return 0.0; // Retorna 0.0 caso não encontre a carteira
    }
}
