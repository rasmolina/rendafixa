package br.edu.ifsp.rendafixa.domain.usescases.transacao;

import br.edu.ifsp.rendafixa.domain.entities.transacao.TipoTransacao;
import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ConsultarTransacao {
    private TransacaoDAO transacaoDAO;

    public ConsultarTransacao(TransacaoDAO transacaoDAO) {
        this.transacaoDAO = transacaoDAO;
    }

    public Optional<Transacao> findOne(Integer id){
        if (Validator.nuloOuVazio(id))
            throw new IllegalArgumentException("ID não pode ser nulo!");
        return transacaoDAO.findOne(id);
    }

    public List<Transacao> findAll(){
        return transacaoDAO.findAll();
    }

    public Optional<Transacao> buscaPorTipo(TipoTransacao tipo){
        return transacaoDAO.buscaPorTipo(tipo);
    }
}
