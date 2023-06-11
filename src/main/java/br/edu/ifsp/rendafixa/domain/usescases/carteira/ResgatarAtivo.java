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

    public ResgatarAtivo(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO, TransacaoDAO transacaoDAO, ItemAtivoDAO itemAtivoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
        this.transacaoDAO = transacaoDAO;
        this.itemAtivoDAO = itemAtivoDAO;
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
                    ItemAtivo itemAtivo = aplicacao;
                    itemAtivoDAO.delete(itemAtivo);
                }

                for(int i=0;i<aplicacoes.size();i++){
                    ativo.getItensAtivo().remove(i);
                }
                ativoDAO.update(ativo);

                Transacao resgate = new Transacao(dataDeHoje,ativo,montanteRendimento, TipoTransacao.VENDA);
                transacaoDAO.create(resgate);

                carteira.setValorDisponivelSaque(valorDisponivelSaque + montanteRendimento);
                carteiraDAO.update(carteira);
                ativoPresenteNaCarteira = true;
                System.out.println("Resgate efetuado com sucesso no valor de R$ " + montanteRendimento);
                break;
            }
        }
        if (!ativoPresenteNaCarteira) {
            System.out.print("Ativo não consta na carteira, resgate não registrado!");
        }
    }



}
