package br.edu.ifsp.rendafixa.domain.usescases.transacao;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.transacao.TipoTransacao;
import br.edu.ifsp.rendafixa.domain.entities.transacao.Transacao;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.util.Optional;

public interface TransacaoDAO extends DAO<Transacao, Integer> {

    Optional<Transacao> buscaPorTipo(TipoTransacao tipo);
}
