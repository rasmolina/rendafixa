package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class CalcularRendimentoAtivo {

    private CarteiraDAO carteiraDAO;
    private ConsultarAtivoCarteira consultarAtivoCarteira;

    public CalcularRendimentoAtivo() {

    }

    public double calcularRendimentoAtivo(Integer idAtivo, LocalDate dataInicial, LocalDate dataFinal){
        if (ChronoUnit.MONTHS.between(dataInicial, dataFinal) > 6) {
            System.out.println("O período máximo para cálculo de rendimento é de 6 meses!");
            return 0.0;
        }

        Optional<Carteira> ativo = consultarAtivoCarteira.buscaPorId(idAtivo);

        LocalDate dataVencimento = ativo.get().getAtivo().getDataVencimento();

        if (dataVencimento.isBefore(LocalDate.now())){
            System.out.println("Ativo vencido e já liquidado!");
            return  0.0;
        }

        if (dataInicial.isAfter(dataFinal) || dataFinal.isAfter(dataVencimento)) {
            System.out.println("Datas inválidas para o cálculo de rendimento!");
            return 0.0;
        }

        double valorInvestido = ativo.get().getValorTotalCompra();;
        double rentabilidade = ativo.get().getAtivo().getRentabilidade();
        double valorIndexador = ativo.get().getAtivo().getIndexador().getValor();
        double taxaPosFixada = ativo.get().getAtivo().getPorcentagemSobreIndexador();
        double variacaoIndexador = (taxaPosFixada - valorIndexador)/100;
        double rendimento = 0.0;
        long meses = ChronoUnit.MONTHS.between(dataInicial, dataFinal);

        //Rendimento pré-fixado = Valor investido x Taxa de rendimento x Período de investimento
        if (ativo.get().getAtivo().getCategoriaRentabilidade().equals("PRE_FIXADO")){
            rendimento = valorInvestido * meses * rentabilidade;
        }

        //Rendimento pós-fixado = Rendimento bruto x (1 + Variação do índice)
        if (ativo.get().getAtivo().getCategoriaRentabilidade().equals("POS_FIXADO")){
            rendimento = (valorInvestido * meses * rentabilidade) * (1+variacaoIndexador);
        }

        return rendimento;
    }
}
