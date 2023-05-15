package br.edu.ifsp.rendafixa.domain.usescases.emissora;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.util.Optional;

public interface EmissoraDAO extends DAO<Emissora, Integer> {
    Optional<Emissora> buscaPorNomeEmissora(String nomeEmissora);

}
