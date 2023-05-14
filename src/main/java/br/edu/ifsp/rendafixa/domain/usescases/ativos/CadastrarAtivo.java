package br.edu.ifsp.rendafixa.domain.usescases.ativos;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.time.LocalDate;

public class CadastrarAtivo {

    private AtivoDAO ativoDAO;

    public CadastrarAtivo(AtivoDAO ativoDAO) {
        this.ativoDAO = ativoDAO;
    }

    public Integer insert(Ativo ativo){
        Validator<Ativo> validator = new ValidadorInclusaoAtivo();
        Notification notification = validator.validate(ativo);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        //Validação para checar se já existe um ativo no banco com o mesmo nome, categoria e data de vencimento
        CategoriaAtivo categoria = ativo.getCategoriaAtivo();
        String nome = ativo.getNome();
        LocalDate vencimento = ativo.getDataVencimento();
        if(ativoDAO.buscaPorCategoria(categoria).isPresent() && ativoDAO.buscaPorNome(nome).isPresent() &&
        ativoDAO.buscaPorVencimento(vencimento).isPresent())
            throw new EntityAlreadyExistsException("Ativo já cadastrado!");

        return ativoDAO.create(ativo);

    }


}
