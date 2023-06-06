package br.edu.ifsp.rendafixa.domain.usescases.carteira;


import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaRentabilidade;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CalcularRendimentoAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;

    public CalcularRendimentoAtivo(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
    }

    //Adaptação: cálculo do rendimento será a partir da data da compra até a data final informada
    public double calcularRendimentoAtivo(Ativo ativo, LocalDate dataFinal) {
        LocalDate dataVencimento = ativo.getDataVencimento();
        double rendimentoTotal = 0.0;
        if (ativo.isLiquidezDiaria()){
            dataVencimento = LocalDate.now();
        }

        if (dataVencimento != null && dataVencimento.isBefore(dataFinal)) {
            dataFinal = dataVencimento; // Utiliza a data de vencimento como nova data final
            System.out.println("Rendimento calculado até " + dataVencimento + "!");
        }

        List<ItemAtivo> aplicacoes = ativo.getItensAtivo();

        if(!aplicacoes.isEmpty()){
            for(ItemAtivo aplicacao : aplicacoes){
                if(aplicacao.getDataDaCompra().isAfter(dataFinal)){
                    System.out.println("Ativo comprado após a data final informada!");
                    return 0.0;
                }

                LocalDate dataCompra = aplicacao.getDataDaCompra();
                long numMeses = ChronoUnit.MONTHS.between(dataCompra, dataFinal);
                double taxaJurosMensal = ativo.getRentabilidade()/12;

                // Cálculo de rendimento para ativo pré-fixado: M = C * (1 + i)^t
                if (ativo.getCategoriaRentabilidade() == CategoriaRentabilidade.PRE_FIXADO) {
                    double montanteAplicacao = aplicacao.getValorDaCompra() * Math.pow(1 + taxaJurosMensal,numMeses);
                    rendimentoTotal += montanteAplicacao;
                }

                // Cálculo de rendimento para ativo pós-fixado: M = (C * (1 + i)^t)*(1* vI)
                if (ativo.getCategoriaRentabilidade() == CategoriaRentabilidade.POS_FIXADO) {
                    double valorIndexador = ativo.getIndexador().getValor();
                    double taxaPosFixado = ativo.getPorcentagemSobreIndexador();
                    double variacaoIndexador = (taxaPosFixado - valorIndexador)/100;
                    double montanteAplicacao = (aplicacao.getValorDaCompra() * Math.pow(1 + taxaJurosMensal,numMeses)) * (1 + variacaoIndexador);
                    rendimentoTotal += montanteAplicacao;
                }
            }
        }

        return rendimentoTotal; //Retorna o montante: valor investido + rendimento até a data informada
    }






}
