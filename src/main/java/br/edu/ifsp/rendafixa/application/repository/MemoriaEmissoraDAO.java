package br.edu.ifsp.rendafixa.application.repository;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.usescases.emissora.EmissoraDAO;

import java.util.*;

public class MemoriaEmissoraDAO implements EmissoraDAO {
    private static final Map<Integer, Emissora> bd = new LinkedHashMap<>();
    private static int idCounter;
    @Override
    public Integer create(Emissora emissora) {
        idCounter++;
        emissora.setId(idCounter);
        bd.put(idCounter, emissora);
        return idCounter;
    }
    @Override
    public Optional<Emissora> buscaPorNomeEmissora(String nomeEmissora) {
        return bd.values().stream()
                .filter(emissora -> emissora.getNome().equals(nomeEmissora))
                .findAny();
    }

    @Override
    public Optional<Emissora> findOne(Integer key) {
        if(bd.containsKey(key))
            return Optional.of(bd.get(key));
        return Optional.empty();
    }

    @Override
    public List<Emissora> findAll() {
        return new ArrayList<>(bd.values());
    }

    @Override
    public boolean update(Emissora emissora) {
        Integer id = emissora.getId();
        if(bd.containsKey(id)){
            bd.replace(id,emissora);
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
    public boolean delete(Emissora emissora) {
        return deleteByKey(emissora.getId());
    }
}
