package br.edu.ifsp.rendafixa.domain.usescases.portadora;

import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

public class RemoverEntidadePortadora {
    private PortadoraDAO portadoraDAO;

    public RemoverEntidadePortadora(PortadoraDAO portadoraDAO) {
        this.portadoraDAO = portadoraDAO;
    }
    public boolean remove(Integer id) {
        if (id == null || portadoraDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Entidade Portadora não localizada!");
        return portadoraDAO.deleteByKey(id);
    }

    public boolean remove(Portadora portadora) {
        if (portadora == null || portadoraDAO.findOne(portadora.getId()).isEmpty())
            throw new EntityNotFoundException("Entidade Portadora não localizada!");
        return portadoraDAO.delete(portadora);
    }
}
