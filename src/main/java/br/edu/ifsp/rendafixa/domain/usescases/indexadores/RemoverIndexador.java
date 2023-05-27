package br.edu.ifsp.rendafixa.domain.usescases.indexadores;

import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

public class RemoverIndexador {

    private IndexadoresDAO indexadoresDAO;

    public RemoverIndexador(IndexadoresDAO indexadoresDAO) {
        this.indexadoresDAO = indexadoresDAO;
    }

    public boolean remove(Integer id) {
        if (id == null || indexadoresDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Indexador não localizado!");
        return indexadoresDAO.deleteByKey(id);
    }

    public boolean remove(Indexador indexador) {
        if (indexador == null || indexadoresDAO.findOne(indexador.getId()).isEmpty())
            throw new EntityNotFoundException("Indexador não localizado!");
        return indexadoresDAO.delete(indexador);
    }
}
