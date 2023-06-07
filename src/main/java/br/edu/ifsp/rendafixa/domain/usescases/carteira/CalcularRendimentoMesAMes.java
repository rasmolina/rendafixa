package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcularRendimentoMesAMes {

    public Map<YearMonth, Double> calcularRendimentoMensal(Ativo ativo, LocalDate dataFinal) {
        LocalDate dataVencimento = ativo.getDataVencimento();
        if (ativo.isLiquidezDiaria()) {
            dataVencimento = LocalDate.now();
        }

        if (dataVencimento != null && dataVencimento.isBefore(dataFinal)) {
            dataFinal = dataVencimento; // Utiliza a data de vencimento como nova data final
            System.out.println("Rendimento calculado até " + dataVencimento + "!");
        }

        Map<YearMonth, Double> rendimentoMensalMap = new HashMap<>();

        List<ItemAtivo> aplicacoes = ativo.getItensAtivo();
        if (!aplicacoes.isEmpty()) {
            for (ItemAtivo aplicacao : aplicacoes) {
                if (aplicacao.getDataDaCompra().isAfter(dataFinal)) {
                    System.out.println("Ativo comprado após a data final informada!");
                    return rendimentoMensalMap;
                }

                LocalDate dataCompra = aplicacao.getDataDaCompra();
                YearMonth inicio = YearMonth.from(dataCompra);
                YearMonth fim = YearMonth.from(dataFinal);
                double taxaJurosMensal = ativo.getRentabilidade() / 12;

                while (inicio.isBefore(fim) || inicio.equals(fim)) {
                    long numMeses = ChronoUnit.MONTHS.between(dataCompra, inicio.atEndOfMonth());
                    double rendimentoMensal = calcularRendimentoMensal(ativo, aplicacao, taxaJurosMensal, numMeses);
                    rendimentoMensalMap.put(inicio, rendimentoMensal);
                    inicio = inicio.plusMonths(1);
                }
                for (Map.Entry<YearMonth, Double> entry : rendimentoMensalMap.entrySet()) {
                    YearMonth month = entry.getKey();
                    double rendimentoMensal = entry.getValue();
                    System.out.println("Rendimento em "+month + ": " + rendimentoMensal);
                }
            }
        }



        return rendimentoMensalMap;
    }

    private double calcularRendimentoMensal(Ativo ativo, ItemAtivo aplicacao, double taxaJurosMensal, long numMeses) {
        if (ativo.getCategoriaRentabilidade() == CategoriaRentabilidade.PRE_FIXADO) {
            return aplicacao.getValorDaCompra() * Math.pow(1 + taxaJurosMensal, numMeses);
        } else if (ativo.getCategoriaRentabilidade() == CategoriaRentabilidade.POS_FIXADO) {
            double valorIndexador = ativo.getIndexador().getValor();
            double taxaPosFixado = ativo.getPorcentagemSobreIndexador();
            double variacaoIndexador = (taxaPosFixado - valorIndexador) / 100;
            return (aplicacao.getValorDaCompra() * Math.pow(1 + taxaJurosMensal, numMeses)) * (1 + variacaoIndexador);
        } else {
            return 0.0;
        }
    }
}
