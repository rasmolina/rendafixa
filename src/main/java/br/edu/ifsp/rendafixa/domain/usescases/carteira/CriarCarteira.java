package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;


public class CriarCarteira {

    private CarteiraDAO carteiraDAO;

    public CriarCarteira(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public Integer insert(Carteira carteira){
        return carteiraDAO.create(carteira);
    }
}
