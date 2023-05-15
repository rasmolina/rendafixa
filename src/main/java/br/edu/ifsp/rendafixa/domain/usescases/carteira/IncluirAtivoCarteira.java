package br.edu.ifsp.rendafixa.domain.usescases.carteira;


import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

public class IncluirAtivoCarteira {

    private CarteiraDAO carteiraDAO;

    public IncluirAtivoCarteira(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public Integer insert(Carteira carteira){
        Validator<Carteira> validator = new ValidadorInclusaoAtivoCarteira();
        Notification notification = validator.validate(carteira);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return carteiraDAO.create(carteira);


    }
}
