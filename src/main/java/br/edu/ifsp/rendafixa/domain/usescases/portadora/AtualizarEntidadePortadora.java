package br.edu.ifsp.rendafixa.domain.usescases.portadora;

import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

public class AtualizarEntidadePortadora {

    private PortadoraDAO portadoraDAO;

    public AtualizarEntidadePortadora(PortadoraDAO portadoraDAO) {
        this.portadoraDAO = portadoraDAO;
    }

    public boolean update(Portadora portadora){
        Validator<Portadora> validator = new ValidadorInclusaoEntidadePortadora();
        Notification notification = validator.validate(portadora);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = portadora.getId();
        if(portadoraDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Entidade Portadora não encontrada!");

        return portadoraDAO.update(portadora);
    }
}
