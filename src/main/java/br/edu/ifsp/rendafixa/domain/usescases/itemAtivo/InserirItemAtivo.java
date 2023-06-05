package br.edu.ifsp.rendafixa.domain.usescases.itemAtivo;

import br.edu.ifsp.rendafixa.domain.entities.itemAtivo.ItemAtivo;

public class InserirItemAtivo {

    private ItemAtivoDAO itemAtivoDAO;

    public InserirItemAtivo(ItemAtivoDAO itemAtivoDAO) {
        this.itemAtivoDAO = itemAtivoDAO;
    }

   public Integer insert(ItemAtivo itemAtivo){
        return itemAtivoDAO.create(itemAtivo);
   }
}
