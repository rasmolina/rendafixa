package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.time.LocalDate;
import java.util.Optional;

public interface CarteiraDAO extends DAO<Carteira, Integer> {

    void IncluirAtivoCarteira(Integer idCarteira, Ativo ativo, double valorCompra);

    void RemoverAtivoCarteira(Integer idCarteira, Ativo ativo, LocalDate dataDaCompra);

    double calcularTotalInvestido();

    void visualizarComposicaoCarteira();

    double calcularRendimentoAtivo(Integer idAtivo, LocalDate dataInicial, LocalDate dataFinal);
}
