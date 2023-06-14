package br.edu.ifsp.rendafixa.application.repository.inmemory;

import br.edu.ifsp.rendafixa.domain.usescases.transacao.TransacaoDAO;
import br.edu.ifsp.rendafixa.domain.entities.transacao.TipoTransacao;
import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;

import java.util.*;

public class MemoriaTransacaoDAO implements TransacaoDAO {

    private static final Map<Integer, Transacao> bd = new LinkedHashMap<>();
    private static int idCounter;
    @Override
    public Optional<Transacao> buscaPorTipo(TipoTransacao tipoTransacao) {
        return bd.values().stream()
                .filter(tipo -> tipo.getTipo().equals(tipoTransacao))
                .findAny();
    }

    @Override
    public Integer create(Transacao transacao) {
        idCounter++;
        transacao.setId(idCounter);
        bd.put(idCounter, transacao);
        return idCounter;
    }

    @Override
    public Optional<Transacao> findOne(Integer key) {
        if(bd.containsKey(key))
            return Optional.of(bd.get(key));
        return Optional.empty();
    }

    @Override
    public List<Transacao> findAll() {
        return new ArrayList<>(bd.values());
    }

    @Override
    public boolean update(Transacao type) {
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Transacao type) {
        return false;
    }
}
