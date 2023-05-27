package br.edu.ifsp.rendafixa.domain.usescases.indexadores;

import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityAlreadyExistsException;

public class CadastrarIndexador {

    private IndexadoresDAO indexadoresDAO;

    public CadastrarIndexador(IndexadoresDAO indexadoresDAO) {
        this.indexadoresDAO = indexadoresDAO;
    }

    public Integer insert(Indexador indexador){
        String nome = indexador.getNome();

        if(indexadoresDAO.buscaPorNome(nome).isPresent())
            throw new EntityAlreadyExistsException("Indexador já cadastrado!");

        return indexadoresDAO.create(indexador);

    }
}
