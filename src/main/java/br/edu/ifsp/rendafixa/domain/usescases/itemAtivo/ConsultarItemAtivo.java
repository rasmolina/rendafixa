package br.edu.ifsp.rendafixa.domain.usescases.itemAtivo;

import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ConsultarItemAtivo {

    private ItemAtivoDAO itemAtivoDAO;

    public ConsultarItemAtivo(ItemAtivoDAO itemAtivoDAO) {
        this.itemAtivoDAO = itemAtivoDAO;
    }

    public Optional<ItemAtivo> findOne(Integer id){
        if (Validator.nuloOuVazio(id))
            throw new IllegalArgumentException("ID não pode ser nulo!");
        return itemAtivoDAO.findOne(id);
    }


    public List<ItemAtivo> findAll(){
        return itemAtivoDAO.findAll();
    }


}
