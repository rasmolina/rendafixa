package br.edu.ifsp.rendafixa.domain.usescases.indexadores;

import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

public class AtualizarIndexador {

    private IndexadoresDAO indexadoresDAO;

    public AtualizarIndexador(IndexadoresDAO indexadoresDAO) {
        this.indexadoresDAO = indexadoresDAO;
    }

    public boolean update(Indexador indexador){
        Integer id = indexador.getId();
        if(indexadoresDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Indexador não encontrado!");

        return indexadoresDAO.update(indexador);
    }
}
