package br.edu.ifsp.rendafixa.application.repository;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.ativos.AtivoDAO;

import java.time.LocalDate;
import java.util.*;

public class MemoriaAtivoDAO implements AtivoDAO {

    private static final Map<Integer, Ativo> bd = new LinkedHashMap<>();
    private static int idCounter;

    //Emulação da inserção no banco de dados bd utilizando Map
    @Override
    public Integer create(Ativo ativo) {
        idCounter++;
        ativo.setId(idCounter);
        bd.put(idCounter, ativo);
        return idCounter;
    }
    @Override
    public Optional<Ativo> buscaPorCategoria(CategoriaAtivo categoriaAtivo) {
        return bd.values().stream()
                .filter(ativo -> ativo.getCategoriaAtivo().equals(categoriaAtivo))
                .findAny();
    }

    @Override
    public Optional<Ativo> buscaPorNome(String nome) {
        return bd.values().stream()
                .filter(ativo -> ativo.getNome().equals(nome))
                .findAny();
    }

    @Override
    public Optional<Ativo> buscaPorVencimento(LocalDate vencimento) {
        return bd.values().stream()
                .filter(ativo -> ativo.getDataVencimento().equals(vencimento))
                .findAny();
    }

    @Override
    public Optional<Ativo> findOne(Integer key) {
        if(bd.containsKey(key)) //Se o bd possuir a chave retorna ela
            return Optional.of(bd.get(key));
        return Optional.empty();
    }

    @Override
    public List<Ativo> findAll() {
        return new ArrayList<>(bd.values());
    }

    @Override
    public boolean update(Ativo ativo) {
        Integer id = ativo.getId();
        if(bd.containsKey(id)){
            bd.replace(id,ativo);
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
    public boolean delete(Ativo ativo) {
        return deleteByKey(ativo.getId());
    }
}
