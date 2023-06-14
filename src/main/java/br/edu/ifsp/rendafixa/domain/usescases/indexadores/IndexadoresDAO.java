package br.edu.ifsp.rendafixa.domain.usescases.indexadores;

import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.util.Optional;

public interface IndexadoresDAO extends DAO<Indexador, Integer> {

    Optional<Indexador> buscaPorNome(String nome);
    Indexador buscar(int id);
}
