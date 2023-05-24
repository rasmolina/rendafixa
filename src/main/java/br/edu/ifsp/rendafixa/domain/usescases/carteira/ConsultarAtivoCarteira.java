package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;


import java.util.List;
import java.util.Optional;

public class ConsultarAtivoCarteira {

    private CarteiraDAO carteiraDAO;

    public ConsultarAtivoCarteira(CarteiraDAO carteiraDAO) {
        this.carteiraDAO = carteiraDAO;
    }

    //Retonar lista de ativos na carteira
    public List<Carteira> findAll(){
        return carteiraDAO.findAll();
    }

    //Filtra por nome
    public Optional<Carteira> buscaPorNome(String nome){
        if (Validator.nuloOuVazio(nome))
            throw new IllegalArgumentException("Nome não pode ser nulo!");
        return carteiraDAO.buscaPorNomeAtivo(nome);
    }

    public Optional<Carteira> buscaPorId(Integer id){
        if (Validator.nuloOuVazio(id))
            throw new IllegalArgumentException("Id inválido!");
        return carteiraDAO.buscaPorIdAtivo(id);
    }

}
