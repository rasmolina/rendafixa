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

public class ComprarAtivo {
    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private TransacaoDAO transacaoDAO;
    private ItemAtivoDAO itemAtivoDAO;

    public ComprarAtivo(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO, TransacaoDAO transacaoDAO, ItemAtivoDAO itemAtivoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
        this.transacaoDAO = transacaoDAO;
        this.itemAtivoDAO = itemAtivoDAO;
    }

    public void comprarAtivo(Carteira carteira, Ativo ativo, double valorCompra, LocalDate dataCompra){
        List<Ativo> ativos = carteira.getAtivos();
        List<ItemAtivo> itemAtivos = ativo.getItensAtivo();
        LocalDate dataVencimentoAtivo = ativo.getDataVencimento();
        LocalDate dataDeHoje = LocalDate.now();

        boolean ativoPresenteNaCarteira = false;
        for (Ativo a : ativos) {
            if (a.equals(ativo)) {
                if(dataVencimentoAtivo.isBefore(dataCompra)){
                    System.out.println("Data da compra é posterior ao vencimento, aplicação não registrada!");
                }else{
                    ItemAtivo aplicacao = new ItemAtivo(ativo,dataCompra,valorCompra);
                    itemAtivoDAO.create(aplicacao);

                    itemAtivos.add(aplicacao);
                    ativo.setItensAtivo(itemAtivos);
                    ativoDAO.update(ativo);

                    Transacao compra = new Transacao(dataDeHoje,dataCompra,ativo,valorCompra,TipoTransacao.COMPRA);
                    transacaoDAO.create(compra);
                    ativoPresenteNaCarteira = true;
                    break;
                }
            }
        }
        if (!ativoPresenteNaCarteira) {
            System.out.print("Ativo não consta na carteira, compra não registrada!");
        }
    }


}
