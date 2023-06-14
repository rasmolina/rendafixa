package br.edu.ifsp.rendafixa.domain.usescases.ativos;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

public class AtualizarAtivo {
    private AtivoDAO ativoDAO;

    public AtualizarAtivo(AtivoDAO ativoDAO) {
        this.ativoDAO = ativoDAO;
    }

    public boolean update(Ativo ativo){
        Validator<Ativo> validator = new ValidadorInclusaoAtivo();
        Notification notification = validator.validate(ativo);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = ativo.getId();
        if(ativoDAO.findOne(id).isEmpty()) {
            throw new EntityNotFoundException("Ativo não encontrado!");
        }

        return ativoDAO.update(ativo);
    }
}
