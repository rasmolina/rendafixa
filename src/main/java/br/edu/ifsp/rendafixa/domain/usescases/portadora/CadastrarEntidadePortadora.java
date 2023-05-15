package br.edu.ifsp.rendafixa.domain.usescases.portadora;

import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

public class CadastrarEntidadePortadora {
    private PortadoraDAO portadoraDAO;
    public CadastrarEntidadePortadora(PortadoraDAO portadoraDAO) {
        this.portadoraDAO = portadoraDAO;
    }

    public Integer insert(Portadora portadora){
        Validator<Portadora> validator = new ValidadorInclusaoEntidadePortadora();
        Notification notification = validator.validate(portadora);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        //Validaçao para checar se já existe a entidade no sistema
        String nomePortadora = portadora.getNome();
        if(portadoraDAO.buscaPorNomePortadora(nomePortadora).isPresent())
            throw new EntityAlreadyExistsException("Portadora já cadastrada!");

        return portadoraDAO.create(portadora);
    }
}
