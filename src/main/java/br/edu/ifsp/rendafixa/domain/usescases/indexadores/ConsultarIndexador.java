package br.edu.ifsp.rendafixa.domain.usescases.indexadores;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.indexadores.Indexador;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ConsultarIndexador {
    private IndexadoresDAO indexadoresDAO;

    public ConsultarIndexador(IndexadoresDAO indexadoresDAO) {
        this.indexadoresDAO = indexadoresDAO;
    }

    public List<Indexador> findAll() { return indexadoresDAO.findAll();}

    public Optional<Indexador> buscaPorNome(String nome){
        if (Validator.nuloOuVazio(nome))
            throw new IllegalArgumentException("Nome do indexador não pode ser nulo!");
        return indexadoresDAO.buscaPorNome(nome);
    }
}
