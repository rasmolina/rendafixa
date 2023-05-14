package br.edu.ifsp.rendafixa.domain.usescases.ativos;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityNotFoundException;

public class RemoverAtivo {

    private AtivoDAO ativoDAO;

    public RemoverAtivo(AtivoDAO ativoDAO) {
        this.ativoDAO = ativoDAO;
    }

    public boolean remove(Integer id) {
        if (id == null || ativoDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Ativo não localizado!");
        return ativoDAO.deleteByKey(id);
    }

    public boolean remove(Ativo ativo) {
        if (ativo == null || ativoDAO.findOne(ativo.getId()).isEmpty())
            throw new EntityNotFoundException("Ativo não localizado!");
        return ativoDAO.delete(ativo);
    }
}
