package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;

import java.time.LocalDate;
import java.util.List;

public class ResgatarAtivosVencidos {

    private CarteiraDAO carteiraDAO;
    private RemoverAtivoCarteira removerAtivoCarteira;

    public ResgatarAtivosVencidos(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public ResgatarAtivosVencidos(Carteira carteira){
        LocalDate dataAtual = LocalDate.now();
        Integer idCarteira = carteira.getId();
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();

            for (Ativo ativo : ativos) {
                LocalDate dataVencimento = ativo.getDataVencimento();
                if (dataVencimento != null && dataVencimento.isBefore(dataAtual)) {
                    removerAtivoCarteira.removerAtivoCarteira(idCarteira, ativo);
                }
            }
        }
    }
}
