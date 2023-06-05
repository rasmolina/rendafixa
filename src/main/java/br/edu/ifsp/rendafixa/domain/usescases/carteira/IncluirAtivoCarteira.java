package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.util.List;

public class IncluirAtivoCarteira {
    private CarteiraDAO carteiraDAO;
    private AtivoDAO ativoDAO;

    public IncluirAtivoCarteira(CarteiraDAO carteiraDAO, AtivoDAO ativoDAO) {
        this.carteiraDAO = carteiraDAO;
        this.ativoDAO = ativoDAO;
    }

    public void incluirAtivoCarteira(Carteira carteira, Ativo ativo){
        if(carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            boolean ativoPresenteNaCarteira = false;
            for (Ativo a : ativos) {
                if (a.equals(ativo)) {
                    System.out.println("Ativo já consta na carteira!");
                    ativoPresenteNaCarteira = true;
                    break;
                }
            }
            if (!ativoPresenteNaCarteira) {
                ativos.add(ativo);
                carteira.setAtivos(ativos);
                carteiraDAO.update(carteira);
                System.out.println("Ativo adicionado com sucesso!");
            }
        }
    }


}
