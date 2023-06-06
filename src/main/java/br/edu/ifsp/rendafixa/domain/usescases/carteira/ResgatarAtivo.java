package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.entities.transacao.TipoTransacao;
import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.ItemAtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.TransacaoDAO;

import java.time.LocalDate;
import java.util.List;

public class ResgatarAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private TransacaoDAO transacaoDAO;
    private ItemAtivoDAO itemAtivoDAO;
    private CalcularTotalInvestidoPorAtivo calcularTotalInvestidoPorAtivo;
    private CalcularRendimentoAtivo calcularRendimentoAtivo;

    public ResgatarAtivo(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO, TransacaoDAO transacaoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
        this.transacaoDAO = transacaoDAO;
    }

    public void resgatarAtivo(Carteira carteira, Ativo ativo){
        List<Ativo> ativos = carteira.getAtivos();
        List<ItemAtivo> aplicacoes = ativo.getItensAtivo();
        LocalDate dataDeHoje = LocalDate.now();
        double valorDisponivelSaque = carteira.getValorDisponivelSaque();
        boolean ativoPresenteNaCarteira = false;
        for (Ativo a : ativos) {
            if (a.equals(ativo)) {
                CalcularRendimentoAtivo rendimento = new CalcularRendimentoAtivo(carteiraDAO,ativoDAO);
                double montanteRendimento = rendimento.calcularRendimentoAtivo(ativo,dataDeHoje);
                for(ItemAtivo aplicacao : aplicacoes){
                    ItemAtivo itemAtivo = new ItemAtivo(ativo,aplicacao.getDataDaCompra(),aplicacao.getValorDaCompra());
                    itemAtivoDAO.delete(itemAtivo);
                    aplicacoes.remove(itemAtivo);
                }
                ativo.setItensAtivo(aplicacoes);
                ativoDAO.update(ativo);

                Transacao resgate = new Transacao(dataDeHoje,ativo,montanteRendimento,TipoTransacao.VENDA);
                transacaoDAO.create(resgate);

                carteira.setValorDisponivelSaque(valorDisponivelSaque + montanteRendimento);
                carteiraDAO.update(carteira);
                ativoPresenteNaCarteira = true;
                break;
            }
        }
        if (!ativoPresenteNaCarteira) {
            System.out.print("Ativo não consta na carteira, resgate não registrado!");
        }
    }



}
