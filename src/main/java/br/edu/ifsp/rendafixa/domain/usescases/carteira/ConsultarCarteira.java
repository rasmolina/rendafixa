package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.util.Optional;

public class ConsultarCarteira {

    private CarteiraDAO carteiraDAO;

    public ConsultarCarteira(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public Optional<Carteira> findOne(Integer id){
        if (Validator.nuloOuVazio(id))
            throw new IllegalArgumentException("ID não pode ser nulo!");
        return carteiraDAO.findOne(id);
    }
}
