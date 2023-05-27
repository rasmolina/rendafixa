package br.edu.ifsp.rendafixa.domain.usescases.transacao;

import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;
public class RegistrarTransacao {

    private TransacaoDAO transacaoDAO;

    public RegistrarTransacao(TransacaoDAO transacaoDAO) {
        this.transacaoDAO = transacaoDAO;
    }

    public Integer insert(Transacao transacao){
        return transacaoDAO.create(transacao);

    }
}
