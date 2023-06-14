package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.util.List;
import java.util.Optional;

public class RemoverAtivoCarteira {
    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;

    public RemoverAtivoCarteira(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
    }

    public void removerAtivoCarteira(Carteira carteira, Ativo ativo) {
        if(carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            boolean ativoPresenteNaCarteira = false;
            for (Ativo a : ativos) {
                if (a.equals(ativo)) {
                    List<ItemAtivo> itensAtivo = ativo.getItensAtivo();
                    if(itensAtivo.isEmpty()){
                        ativos.remove(ativo);
                        carteira.setAtivos(ativos);
                        carteiraDAO.update(carteira);
                        System.out.println("Ativo removido com sucesso!");
                    }else{
                        System.out.println("Ativo possui aplicação e não pode ser removido da carteira!");
                    }
                    ativoPresenteNaCarteira = true;
                    break;
                }
            }
            if (!ativoPresenteNaCarteira) {
                System.out.println("Ativo não consta na carteira!");

            }
        }

    }


}
