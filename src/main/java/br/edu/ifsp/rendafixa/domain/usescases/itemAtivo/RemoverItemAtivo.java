package br.edu.ifsp.rendafixa.domain.usescases.itemAtivo;

import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

public class RemoverItemAtivo {

    private ItemAtivoDAO itemAtivoDAO;

    public RemoverItemAtivo(ItemAtivoDAO itemAtivoDAO) {
        this.itemAtivoDAO = itemAtivoDAO;
    }

    public boolean remove(Integer id){
        if (id == null || itemAtivoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Ativo não localizado!");
        return itemAtivoDAO.deleteByKey(id);
    }

    public boolean remove(ItemAtivo itemAtivo){
        if (itemAtivo == null || itemAtivoDAO.findOne(itemAtivo.getId()).isEmpty())
            throw new EntityNotFoundException("Aplicação não localizada!");
        return itemAtivoDAO.delete(itemAtivo);
    }


}
