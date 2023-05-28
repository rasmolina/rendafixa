package br.edu.ifsp.rendafixa.domain.usescases.carteira;


import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CalcularRendimentoAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private ConsultarCarteira consultarCarteira;

    //Adaptação: cálculo do rendimento será a partir da data da compra até a data final informada
    //Se o ativo tiver compras antes da data inicial informada, ela não será considerada para o cálculo do rendimento médio
    public double calcularRendimentoAtivo(Integer idCarteira, Ativo ativo, LocalDate dataInicial, LocalDate dataFinal) {
        LocalDate dataVencimento = ativo.getDataVencimento();
        if (ativo.isLiquidezDiaria()){
            dataVencimento = LocalDate.now();
        }
        if (dataInicial.isAfter(dataFinal) || dataFinal.isBefore(dataInicial) || ChronoUnit.MONTHS.between(dataInicial, dataFinal) > 12 ||
                dataInicial.isAfter(dataVencimento)) {
            System.out.println("Período inválido para o cálculo de rendimento!");
            return 0.0;
        }else{
            Carteira carteira = consultarCarteira.buscarCarteiraPorId(idCarteira);
            if (carteira == null) {
                return 0.0; // Retorna 0.0 caso não encontre a carteira
            }

            List<Ativo> ativos = carteira.getAtivos();
            double rendimentoTotal = 0.0;
            int qtdeCompras = 0;

            // Verificar se a data de vencimento do ativo é anterior à data final informada
            if (dataVencimento != null && dataVencimento.isBefore(dataFinal)) {
                dataFinal = dataVencimento; // Utilizar a data de vencimento como nova data final
                System.out.println("Rendimento calculado até " + dataVencimento + "!");
            }

            for (Ativo a : ativos) {
                if (a.equals(ativo)) {
                    List<LocalDate> datas = a.getDataDaCompra();
                    List<Double> valoresCompra = a.getValorTotalDaCompra();
                    int qtdeCotas = datas.size(); // Cada compra está sendo considerada como uma cota

                    for (int i = 0; i < qtdeCotas; i++) {
                        LocalDate dataCompra = datas.get(i);
                        double valorCompra = valoresCompra.get(i);

                        if (dataCompra.isAfter(dataInicial) && dataCompra.isBefore(dataFinal)) {

                            long numMeses = ChronoUnit.MONTHS.between(dataCompra, dataFinal);
                            double rentabilidade = a.getRentabilidade();

                            // Rendimento pré-fixado = Valor investido x renatabilidade x Período de investimento
                            if (a.getCategoriaRentabilidade() == CategoriaRentabilidade.PRE_FIXADO) {
                                double rendimentoCompra = valorCompra * rentabilidade * numMeses;
                                rendimentoTotal += rendimentoCompra;
                                qtdeCompras++;
                            }

                            // Rendimento pós-fixado = (Valor investido x Período de investimento x Rentabilidade) x (1 + Variação do Indexador)
                            if (a.getCategoriaRentabilidade() == CategoriaRentabilidade.POS_FIXADO) {
                                double valorIndexador = a.getIndexador().getValor();
                                double taxaPosFixado = a.getPorcentagemSobreIndexador();
                                double variacaoIndexador = (taxaPosFixado - valorIndexador)/100;
                                double rendimentoCompra = (valorCompra * numMeses * rentabilidade) * (1 + variacaoIndexador);
                                rendimentoTotal += rendimentoCompra;
                                qtdeCompras++;
                            }
                        }
                    }
                }
            }

            if (qtdeCompras > 0) {
                return rendimentoTotal;
            }

            return 0.0; // Retorna 0.0 caso não haja compras do ativo no intervalo de datas
        }
    }


}
