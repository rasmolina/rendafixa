package br.edu.ifsp.rendafixa.application.repository;

import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.usescases.indexadores.IndexadoresDAO;

import java.util.*;

public class MemoriaIndexadoresDAO implements IndexadoresDAO {
    private static final Map<Integer, Indexador> bd = new LinkedHashMap<>();
    private static int idCounter;
    @Override
    public Optional<Indexador> findOne(Integer key) {
        if(bd.containsKey(key))
            return Optional.of(bd.get(key));
        return Optional.empty();
    }

    @Override
    public List<Indexador> findAll() {
        return new ArrayList<>(bd.values());
    }

    @Override
    public Optional<Indexador> buscaPorNome(String nome) {
        return bd.values().stream()
                .filter(indexador -> indexador.getNome().equals(nome))
                .findAny();
    }

    @Override
    public boolean update(Indexador indexador) {
        Integer id = indexador.getId();
        if(bd.containsKey(id)){
            bd.replace(id,indexador);
            return true;
        }
        return false;
    }

    //Não serão utilizados
    @Override
    public Integer create(Indexador type) {
        return null;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Indexador type) {
        return false;
    }

}
