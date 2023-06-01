package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

import java.util.List;

public class VisualizarTotalInvestido {
    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;

    public VisualizarTotalInvestido(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
    }

    public double calcularTotalInvestido(Integer idCarteira) {
        Carteira carteira = carteiraDAO.findOne(idCarteira)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado!"));

        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            double totalInvestido = 0.0;

            for (Ativo ativo : ativos) {
                List<Double> valoresCompra = ativo.getValorTotalDaCompra();

                for (double valorCompra : valoresCompra) {
                    totalInvestido += valorCompra;
                }
            }

            return totalInvestido;
        }

        return 0.0; // Retorna 0.0 caso não encontre a carteira
    }
}
