package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ConsultarCarteira {

    private CarteiraDAO carteiraDAO;

    private List<Carteira> carteiras;

    public ConsultarCarteira(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public Optional<Carteira> findOne(Integer id){
        if (Validator.nuloOuVazio(id))
            throw new IllegalArgumentException("ID não pode ser nulo!");
        return carteiraDAO.findOne(id);
    }

    public Carteira buscarCarteiraPorId(Integer idCarteira){
        for(Carteira carteira : carteiras){
            if(carteira.getId() == idCarteira)
                return carteira;
        }
        return null;
    }
}
