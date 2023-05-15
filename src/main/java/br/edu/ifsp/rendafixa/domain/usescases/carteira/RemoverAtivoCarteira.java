package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

public class RemoverAtivoCarteira {
    private CarteiraDAO carteiraDAO;

    public RemoverAtivoCarteira(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    public boolean remove(Integer id) {
        if (id == null || carteiraDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Ativo não localizado na carteira!");
        return carteiraDAO.deleteByKey(id);
    }

    public boolean remove(Carteira carteira) {
        if (carteira == null || carteiraDAO.findOne(carteira.getId()).isEmpty())
            throw new EntityNotFoundException("Ativo não localizado na carteira!");
        return carteiraDAO.delete(carteira);
    }
}
