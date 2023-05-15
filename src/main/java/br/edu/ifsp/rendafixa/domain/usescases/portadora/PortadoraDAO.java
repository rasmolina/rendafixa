package br.edu.ifsp.rendafixa.domain.usescases.portadora;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.util.Optional;

public interface PortadoraDAO extends DAO<Portadora, Integer> {
    Optional<Emissora> buscaPorNomePortadora(String nomePortadora);

}
