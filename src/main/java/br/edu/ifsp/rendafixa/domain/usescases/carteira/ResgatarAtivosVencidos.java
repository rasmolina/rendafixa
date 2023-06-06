package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;

import java.time.LocalDate;
import java.util.List;

public class ResgatarAtivosVencidos {

    private CarteiraDAO carteiraDAO;
    private ResgatarAtivo resgatarAtivo;

    public ResgatarAtivosVencidos(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public ResgatarAtivosVencidos(Carteira carteira){
        boolean ativoVencidoEncontrado = false;
        LocalDate dataAtual = LocalDate.now();
        if (carteira != null) {
            List<Ativo> ativos = carteira.getAtivos();
            if(!ativos.isEmpty()){
                for (Ativo ativo : ativos) {
                    LocalDate dataVencimento = ativo.getDataVencimento();
                    if (dataVencimento != null && dataVencimento.isBefore(dataAtual)) {
                        ativoVencidoEncontrado = true;
                        resgatarAtivo.resgatarAtivo(carteira,ativo);
                    }
                }
                if(!ativoVencidoEncontrado)
                    System.out.println("Carteira não possui ativos vencidos!");
            }else{
                System.out.println("Carteira sem ativos!");
            }

        }
    }
}
