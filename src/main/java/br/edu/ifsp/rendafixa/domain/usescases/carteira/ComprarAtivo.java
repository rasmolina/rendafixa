package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.time.LocalDate;
import java.util.List;

public class ComprarAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private ConsultarCarteira consultarCarteira;

    private void IncluirAtivoCarteira(Integer idCarteira, Ativo ativo, double valorCompra){
        Carteira carteira = consultarCarteira.buscarCarteiraPorId(idCarteira);
        if(carteira != null){
            List<Ativo> ativos = carteira.getAtivos();
            boolean ativoPresenteNaCarteira = false;
            for (Ativo a : ativos) {
                if (a.equals(ativo)) {
                    a.getDataDaCompra().add(LocalDate.now());
                    a.getValorTotalDaCompra().add(valorCompra);
                    ativoPresenteNaCarteira = true;
                    break;
                }
            }
            if (!ativoPresenteNaCarteira) {
                ativo.getDataDaCompra().add(LocalDate.now());
                ativo.getValorTotalDaCompra().add(valorCompra);
                ativos.add(ativo);
            }
            ativoDAO.update(ativo);
            carteira.setAtivos(ativos);
            carteiraDAO.update(carteira);
        }
    }


}
