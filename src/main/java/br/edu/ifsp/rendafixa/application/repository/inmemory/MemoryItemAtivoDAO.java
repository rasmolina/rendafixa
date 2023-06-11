package br.edu.ifsp.rendafixa.application.repository.inmemory;

import br.edu.ifsp.rendafixa.domain.usescases.itemAtivo.ItemAtivoDAO;
import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;

import java.util.*;

public class MemoryItemAtivoDAO implements ItemAtivoDAO {
    private static final Map<Integer, ItemAtivo> bd = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(ItemAtivo itemAtivo) {
        idCounter++;
        itemAtivo.setId(idCounter);
        bd.put(idCounter, itemAtivo);
        return idCounter;
    }

    @Override
    public Optional<ItemAtivo> findOne(Integer key) {
        if(bd.containsKey(key))
            return Optional.of(bd.get(key));
        return Optional.empty();
    }

    @Override
    public List<ItemAtivo> findAll() {
        return new ArrayList<>(bd.values());
    }

    @Override
    public boolean update(ItemAtivo itemAtivo) {
        Integer id = itemAtivo.getId();
        if(bd.containsKey(id)){
            bd.replace(id,itemAtivo);
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
    public boolean delete(ItemAtivo itemAtivo) {
        return deleteByKey(itemAtivo.getId());
    }
}
