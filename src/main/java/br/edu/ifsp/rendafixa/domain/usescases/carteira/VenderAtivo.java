package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.transacao.TipoTransacao;
import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.TransacaoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public class VenderAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private TransacaoDAO transacaoDAO;

    public VenderAtivo(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO, TransacaoDAO transacaoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
        this.transacaoDAO = transacaoDAO;
    }

    public void RemoverCompraAtivoCarteira(Integer idCarteira, Ativo ativo, LocalDate dataDaCompra) {
        Carteira carteira = carteiraDAO.findOne(idCarteira)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado!"));

        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            int index = ativos.indexOf(ativo);
            if (index != -1) {
                Ativo ativoEncontrado = ativos.get(index);
                List<LocalDate> datas = ativoEncontrado.getDataDaCompra();
                List<Double> valorInvestido = ativoEncontrado.getValorTotalDaCompra();
                int compraIndex = datas.indexOf(dataDaCompra);
                if (compraIndex != -1) {
                    datas.remove(compraIndex);
                    valorInvestido.remove(compraIndex);
                    ativoDAO.update(ativoEncontrado);
                    carteira.setAtivos(ativos);
                    carteiraDAO.update(carteira);

                    // Remover o ativo da carteira se não houver mais compras
                    if (datas.isEmpty() || valorInvestido.isEmpty()) {
                        ativos.remove(ativoEncontrado);
                        carteira.setAtivos(ativos);
                        carteiraDAO.update(carteira);
                    }

                    double valorCompra = ativoEncontrado.getValorTotalDaCompra().get(compraIndex);
                    double rendimento = carteiraDAO.calcularRendimentoAtivo(idCarteira, ativoEncontrado,dataDaCompra,LocalDate.now());

                    double valorSaque = carteira.getValorDisponivelSaque();
                    valorSaque += valorCompra + rendimento;
                    carteira.setValorDisponivelSaque(valorSaque);
                    carteiraDAO.update(carteira);

                    Transacao venda = new Transacao(LocalDate.now(),LocalDate.now(),dataDaCompra,ativo,valorCompra,TipoTransacao.VENDA);
                    transacaoDAO.create(venda);
                }
            }
        }
    }


}
