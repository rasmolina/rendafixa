package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.time.LocalDate;
import java.util.List;

public class RemoverAtivoCarteira {
    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private ConsultarCarteira consultarCarteira;

    public void removerAtivoCarteira(Integer idCarteira, Ativo ativo) {
        Carteira carteira = consultarCarteira.buscarCarteiraPorId(idCarteira);
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            if (ativos.contains(ativo)) {
                List<LocalDate> datasDaCompra = ativo.getDataDaCompra();

                for (LocalDate dataDaCompra : datasDaCompra) {
                    // Remove cada compra do ativo e por fim remove o ativo da carteira
                    carteiraDAO.RemoverCompraAtivoCarteira(idCarteira, ativo, dataDaCompra);
                }
            }
        }
    }


}