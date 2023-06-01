package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.transacao.TipoTransacao;
import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.transacao.TransacaoDAO;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComprarAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private TransacaoDAO transacaoDAO;

    public ComprarAtivo(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO, TransacaoDAO transacaoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
        this.transacaoDAO = transacaoDAO;
    }

    public void IncluirAtivoCarteira(Integer idCarteira, Ativo ativo, double valorCompra, LocalDate dataCompra){
        Carteira carteira = carteiraDAO.findOne(idCarteira)
                .orElseThrow(() -> new EntityNotFoundException("Id não encontrado!"));
        if(carteira != null){
            List<Ativo> ativos = carteira.getAtivos();
            List<LocalDate> datasCompras = ativo.getDataDaCompra();
            List<Double> valoresCompras = ativo.getValorTotalDaCompra();
            boolean ativoPresenteNaCarteira = false;
            for (Ativo a : ativos) {
                if (a.equals(ativo)) {
                    datasCompras.add(dataCompra);
                    a.setDataDaCompra(datasCompras);
                    valoresCompras.add(valorCompra);
                    a.setValorTotalDaCompra(valoresCompras);
                    ativoPresenteNaCarteira = true;
                    break;
                }
            }
            if (!ativoPresenteNaCarteira) {
                datasCompras.add(dataCompra);
                ativo.setDataDaCompra(datasCompras);
                valoresCompras.add(valorCompra);
                ativo.setValorTotalDaCompra(valoresCompras);
                ativos.add(ativo);
            }
            ativoDAO.update(ativo);
            carteira.setAtivos(ativos);
            carteiraDAO.update(carteira);
            Transacao compra = new Transacao(LocalDate.now(),LocalDate.now(),ativo,valorCompra,TipoTransacao.COMPRA);
            transacaoDAO.create(compra);
        }
    }


}
