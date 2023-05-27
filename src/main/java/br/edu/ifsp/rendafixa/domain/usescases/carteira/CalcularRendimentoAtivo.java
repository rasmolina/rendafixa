package br.edu.ifsp.rendafixa.domain.usescases.carteira;


import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CalcularRendimentoAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private ConsultarCarteira consultarCarteira;

    public double calcularRendimentoAtivo(Integer idCarteira, Ativo ativo, LocalDate dataInicial, LocalDate dataFinal) {
        Carteira carteira = consultarCarteira.buscarCarteiraPorId(idCarteira);
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            double rendimentoTotal = 0.0;
            int qtdeCompras = 0;

            for (Ativo a : ativos) {
                if (a.equals(ativo)) {
                    List<LocalDate> datas = a.getDataDaCompra();
                    List<Double> valoresCompra = a.getValorTotalDaCompra();
                    int qtdeCotas = datas.size(); //Cada compra está sendo considerada como uma cota

                    for (int i = 0; i < qtdeCotas; i++) {
                        LocalDate dataCompra = datas.get(i);
                        double valorCompra = valoresCompra.get(i);

                        if (dataCompra.isAfter(dataInicial) && dataCompra.isBefore(dataFinal)) {
                            long numMeses = ChronoUnit.MONTHS.between(dataCompra, dataFinal);
                            double rentabilidade = a.getRentabilidade();
                            //Rendimento pré-fixado = Valor investido x Taxa de rendimento x Período de investimento
                            double rendimentoCompra = valorCompra * rentabilidade * numMeses;
                            rendimentoTotal += rendimentoCompra;
                            qtdeCompras++;
                        }
                    }
                }
            }

            if (qtdeCompras > 0) {
                double rendimentoMedio = rendimentoTotal / qtdeCompras;
                return rendimentoMedio;
            }
        }

        return 0.0; // Retorna 0.0 caso não encontre a carteira ou não haja compras do ativo no intervalo de datas
    }



}
