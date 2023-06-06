package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualizarComposicaoCarteira {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private CalcularTotalInvestido calcularTotalInvestido;
    private CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;

    public VisualizarComposicaoCarteira(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
    }

    public void visualizarComposicaoCarteira(Carteira carteira) {
          if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            CalcularTotalInvestido investido = new CalcularTotalInvestido(carteiraDAO,ativoDAO,calcularTotalInvestidoPorAtivo);
            double totalInvestido = investido.calcularTotalInvestido(carteira);

            Map<CategoriaAtivo, Double> composicaoRelativa = new HashMap<>();

            for (Ativo ativo : ativos) {
                CategoriaAtivo categoria = ativo.getCategoriaAtivo();
                CalcularTotalInvestidoPorAtivo investidoAtivo = new CalcularTotalInvestidoPorAtivo(carteiraDAO,ativoDAO);
                double valorInvestido = investidoAtivo.calcularTotalInvestidoPorAtivo(ativo);

                // Atualiza a composição relativa por categoria
                double composicaoAtual = composicaoRelativa.getOrDefault(categoria, 0.0);
                composicaoAtual += valorInvestido / totalInvestido;
                composicaoRelativa.put(categoria, composicaoAtual);
            }

            // Exibe a composição absoluta e relativa por categoria
            System.out.println("Composição da Carteira:");
            System.out.println("-------------------------------");
            for (Map.Entry<CategoriaAtivo, Double> entry : composicaoRelativa.entrySet()) {
                CategoriaAtivo categoria = entry.getKey();
                double valorComposicaoAbsoluta = entry.getValue() * totalInvestido;
                double valorComposicaoRelativa = entry.getValue() * 100.0;
                System.out.printf("Categoria: %s\n", categoria);
                System.out.printf("Composição Absoluta: %.2f\n", valorComposicaoAbsoluta);
                System.out.printf("Composição Relativa: %.2f%%\n", valorComposicaoRelativa);
                System.out.println("-------------------------------");
            }
        }
    }


}
