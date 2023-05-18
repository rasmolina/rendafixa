package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.util.Optional;

public interface CarteiraDAO extends DAO<Carteira, Integer> {

    Optional<Carteira> buscaPorNomeAtivo(String nome);

    double CalcularTotalInvestido();
}
