package br.edu.ifsp.rendafixa.domain.usescases.ativos;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConsultarAtivo {

    private AtivoDAO ativoDAO;

    public ConsultarAtivo(AtivoDAO ativoDAO) {
        this.ativoDAO = ativoDAO;
    }

    public Optional<Ativo> findOne(Integer id){
        if (Validator.nuloOuVazio(id))
            throw new IllegalArgumentException("ID não pode ser nulo!");
        return ativoDAO.findOne(id);
    }

    public Optional<Ativo> buscaPorNome(String nome){
        if (Validator.nuloOuVazio(nome))
            throw new IllegalArgumentException("Nome não pode ser nulo!");
        return ativoDAO.buscaPorNome(nome);
    }

    public Optional<Ativo> buscaPorCategoria(CategoriaAtivo categoria){
        return ativoDAO.buscaPorCategoria(categoria);
    }

    public List<Ativo> findAll(){
        return ativoDAO.findAll();
    }
}
