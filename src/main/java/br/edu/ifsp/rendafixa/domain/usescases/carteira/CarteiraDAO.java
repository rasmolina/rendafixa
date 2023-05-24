package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.time.LocalDate;
import java.util.Optional;

public interface CarteiraDAO extends DAO<Carteira, Integer> {

    Optional<Carteira> buscaPorNomeAtivo(String nome);

    Optional<Carteira> buscaPorIdAtivo(Integer id);

    double calcularTotalInvestido();

    void visualizarComposicaoCarteira();

    double calcularRendimentoAtivo(Integer idAtivo, LocalDate dataInicial, LocalDate dataFinal);
}
