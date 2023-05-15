package br.edu.ifsp.rendafixa.domain.usescases.emissora;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

public class AtualizarEntidadeEmissora {

    private EmissoraDAO emissoraDAO;

    public AtualizarEntidadeEmissora(EmissoraDAO emissoraDAO) {
        this.emissoraDAO = emissoraDAO;
    }

    public boolean update(Emissora emissora){
        Validator<Emissora> validator = new ValidadorInclusaoEntidadeEmissora();
        Notification notification = validator.validate(emissora);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = emissora.getId();
        if(emissoraDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Entidade Emissora não encontrada!");

        return emissoraDAO.update(emissora);
    }
}
