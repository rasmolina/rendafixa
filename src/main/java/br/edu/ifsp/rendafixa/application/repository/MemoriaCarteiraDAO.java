package br.edu.ifsp.rendafixa.application.repository;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.carteira.CarteiraDAO;

import java.time.LocalDate;
import java.util.*;

public class MemoriaCarteiraDAO implements CarteiraDAO {
    private static final Map<Integer, Carteira> bd = new LinkedHashMap<>();
    private static int idCounter;
    @Override
    public Optional<Carteira> buscaPorNomeAtivo(String nome) {
        return bd.values().stream()
                .filter(carteira -> carteira.getAtivo().getNome().equals(nome))
                .findAny();
    }

    @Override
    public Optional<Carteira> buscaPorIdAtivo(Integer id) {
        return bd.values().stream()
                .filter(carteira -> carteira.getAtivo().getId() == id)
                .findAny();
    }

    @Override
    public double calcularTotalInvestido() {
        return 0;
    }

    @Override
    public void visualizarComposicaoCarteira() {

    }

    @Override
    public double calcularRendimentoAtivo(Integer idAtivo, LocalDate dataInicial, LocalDate dataFinal) {
        return 0;
    }


    @Override
    public Integer create(Carteira carteira) {
        idCounter++;
        carteira.setId(idCounter);
        bd.put(idCounter, carteira);
        return idCounter;
    }

    @Override
    public Optional<Carteira> findOne(Integer key) {
        if(bd.containsKey(key))
            return Optional.of(bd.get(key));
        return Optional.empty();
    }

    @Override
    public List<Carteira> findAll() {
        return new ArrayList<>(bd.values());
    }

    @Override
    public boolean update(Carteira carteira) {
        Integer id = carteira.getId();
        if(bd.containsKey(id)){
            bd.replace(id,carteira);
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
    public boolean delete(Carteira carteira) {
        return deleteByKey(carteira.getId());
    }
}
