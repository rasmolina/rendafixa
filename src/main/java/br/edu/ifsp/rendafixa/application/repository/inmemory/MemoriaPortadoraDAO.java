package br.edu.ifsp.rendafixa.application.repository.inmemory;

import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.portadora.PortadoraDAO;

import java.util.*;

public class MemoriaPortadoraDAO implements PortadoraDAO {
    private static final Map<Integer, Portadora> bd = new LinkedHashMap<>();
    private static int idCounter;
    @Override
    public Integer create(Portadora portadora) {
        idCounter++;
        portadora.setId(idCounter);
        bd.put(idCounter, portadora);
        return idCounter;
    }
    @Override
    public Optional<Portadora> buscaPorNomePortadora(String nomePortadora) {
        return bd.values().stream()
                .filter(portadora -> portadora.getNome().equals(nomePortadora))
                .findAny();
    }

    @Override
    public Optional<Portadora> findOne(Integer key) {
        if(bd.containsKey(key))
            return Optional.of(bd.get(key));
        return Optional.empty();
    }

    @Override
    public List<Portadora> findAll() {
        return new ArrayList<>(bd.values());
    }

    @Override
    public boolean update(Portadora portadora) {
        Integer id = portadora.getId();
        if(bd.containsKey(id)){
            bd.replace(id,portadora);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if(bd.containsKey(key)){
            bd.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Portadora portadora) {
        return deleteByKey(portadora.getId());
    }
}
