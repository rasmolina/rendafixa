package br.edu.ifsp.rendafixa.domain.usescases.portadora;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.emissora.EmissoraDAO;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ConsultarPortadora {
    private PortadoraDAO portadoraDAO;

    public ConsultarPortadora(PortadoraDAO portadoraDAO) {
        this.portadoraDAO = portadoraDAO;
    }

    public Optional<Portadora> findOne(Integer id){
        if (Validator.nuloOuVazio(id))
            throw new IllegalArgumentException("ID não pode ser nulo!");
        return portadoraDAO.findOne(id);
    }

    public List<Portadora> findAll(){
        return portadoraDAO.findAll();
    }

}

