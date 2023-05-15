package br.edu.ifsp.rendafixa.domain.usescases.emissora;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

public class CadastrarEntidadeEmissora {
    private EmissoraDAO emissoraDAO;

    public CadastrarEntidadeEmissora(EmissoraDAO emissoraDAO) {
        this.emissoraDAO = emissoraDAO;
    }

    public Integer insert(Emissora emissora){
        Validator<Emissora> validator = new ValidadorInclusaoEntidadeEmissora();
        Notification notification = validator.validate(emissora);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        //Validaçao para checar se já existe a entidade no sistema
        String nomeEmissora = emissora.getNome();
        if(emissoraDAO.buscaPorNomeEmissora(nomeEmissora).isPresent())
            throw new EntityAlreadyExistsException("Emissora já cadastrada!");

        return emissoraDAO.create(emissora);
    }
}
