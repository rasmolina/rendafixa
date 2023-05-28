package br.edu.ifsp.rendafixa.application.repository;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.carteira.CarteiraDAO;

import java.time.LocalDate;
import java.util.*;

public class MemoriaCarteiraDAO implements CarteiraDAO {
    private static final Map<Integer, Carteira> bd = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Carteira buscarCarteiraPorId(Integer idCarteira) {
        return null;
    }

    @Override
    public void IncluirAtivoCarteira(Integer idCarteira, Ativo ativo, double valorCompra) {

    }

    @Override
    public void RemoverCompraAtivoCarteira(Integer idCarteira, Ativo ativo, LocalDate dataDaCompra) {

    }

    @Override
    public double calcularTotalInvestido(Integer idCarteira) {
        return 0;
    }

    @Override
    public void visualizarComposicaoCarteira(Integer idCarteira) {

    }

    @Override
    public double calcularTotalInvestidoPorAtivo(Ativo ativo) {
        return 0;
    }

    @Override
    public double calcularRendimentoAtivo(Integer idCarteira, Ativo ativo, LocalDate dataInicial, LocalDate dataFinal) {
        return 0;
    }

    @Override
    public void consultarAtivoNaCarteira(Integer idCarteira, Ativo ativo) {

    }

    @Override
    public void listarAtivosNaCarteira(Integer idCarteira) {

    }

    @Override
    public void removerAtivoCarteira(Integer idCarteira, Ativo ativo) {

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
