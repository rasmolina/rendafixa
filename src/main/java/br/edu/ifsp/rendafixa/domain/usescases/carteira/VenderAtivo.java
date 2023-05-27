package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.time.LocalDate;
import java.util.List;

public class VenderAtivo {

    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;
    private List<Carteira> carteiras;

    public Carteira buscarCarteiraPorId(Integer idCarteira){
        for(Carteira carteira : carteiras){
            if(carteira.getId() == idCarteira)
                return carteira;
        }
        return null;
    }

    private void RemoverAtivoCarteira(Integer idCarteira, Ativo ativo, LocalDate dataDaCompra) {
        Carteira carteira = buscarCarteiraPorId(idCarteira);
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
                }
            }
        }
    }


}
