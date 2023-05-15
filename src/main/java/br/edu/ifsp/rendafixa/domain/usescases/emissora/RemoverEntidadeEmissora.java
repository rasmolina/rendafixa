package br.edu.ifsp.rendafixa.domain.usescases.emissora;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

public class RemoverEntidadeEmissora {

    private EmissoraDAO emissoraDAO;

    public RemoverEntidadeEmissora(EmissoraDAO emissoraDAO) {
        this.emissoraDAO = emissoraDAO;
    }

    public boolean remove(Integer id) {
        if (id == null || emissoraDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Entidade Emissora não localizada!");
        return emissoraDAO.deleteByKey(id);
    }

    public boolean remove(Emissora emissora) {
        if (emissora == null || emissoraDAO.findOne(emissora.getId()).isEmpty())
            throw new EntityNotFoundException("Entidade Emissora não localizada!");
        return emissoraDAO.delete(emissora);
    }
}
