package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.time.LocalDate;

public interface CarteiraDAO extends DAO<Carteira, Integer> {
    void incluirAtivoCarteira(Carteira carteira, Ativo ativo);
    boolean removerAtivoCarteira(Carteira carteira, Ativo ativo);
    void comprarAtivo(Carteira carteira, Ativo ativo, double valorCompra, LocalDate dataCompra);
    void resgatarAtivo(Carteira carteira, Ativo ativo);
    void ResgatarAtivosVencidos(Carteira carteira);
    boolean consultarAtivoNaCarteira(Integer idCarteira, Ativo ativo);
    double CalcularTotalInvestidoPorAtivo(Ativo ativo);
    Carteira buscar(int id);

}
