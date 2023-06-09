package br.edu.ifsp.rendafixa.domain.usescases.portadora;

import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.util.Optional;

public interface PortadoraDAO extends DAO<Portadora, Integer> {
    Optional<Portadora> buscaPorNomePortadora(String nomePortadora);
    Portadora buscar(int id);
}
