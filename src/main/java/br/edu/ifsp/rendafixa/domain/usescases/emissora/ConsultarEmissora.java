package br.edu.ifsp.rendafixa.domain.usescases.emissora;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;
import java.util.List;
import java.util.Optional;

public class ConsultarEmissora {
    private EmissoraDAO emissoraDAO;

    public ConsultarEmissora(EmissoraDAO emissoraDAO) {
        this.emissoraDAO = emissoraDAO;
    }

    public Optional<Emissora> findOne(Integer id){
        if (Validator.nuloOuVazio(id))
            throw new IllegalArgumentException("ID não pode ser nulo!");
        return emissoraDAO.findOne(id);
    }

    public List<Emissora> findAll(){
        return emissoraDAO.findAll();
    }
}
