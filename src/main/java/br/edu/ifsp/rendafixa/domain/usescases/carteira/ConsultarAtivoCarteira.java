package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

import java.util.List;

public class ConsultarAtivoCarteira {
    private CarteiraDAO carteiraDAO;
    private CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;

    public ConsultarAtivoCarteira(CarteiraDAO carteiraDAO, CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo) {
        this.carteiraDAO = carteiraDAO;
        this.calcularTotalInvestidoPorAtivo = calcularTotalInvestidoPorAtivo;
    }

    public void consultarAtivoNaCarteira(Integer idCarteira, Ativo ativo) {
        Carteira carteira = carteiraDAO.findOne(idCarteira)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado!"));

        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            if (ativos.contains(ativo)) {
                double totalInvestido = calcularTotalInvestidoPorAtivo.calcularTotalInvestidoPorAtivo(ativo);
                System.out.println("Ativo: " + ativo.getNome());
                System.out.println("Categoria: " + ativo.getCategoriaAtivo());
                System.out.printf("Total investido: %.2f\n", totalInvestido);
            } else {
                System.out.println("O ativo buscado não está presente na carteira!");
            }
        }
    }



}
