package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.time.LocalDate;

public interface CarteiraDAO extends DAO<Carteira, Integer> {

    Carteira buscarCarteiraPorId(Integer idCarteira);
    void IncluirAtivoCarteira(Integer idCarteira, Ativo ativo, double valorCompra);

    void RemoverCompraAtivoCarteira(Integer idCarteira, Ativo ativo, LocalDate dataDaCompra);

    double calcularTotalInvestido(Integer idCarteira);

    void visualizarComposicaoCarteira(Integer idCarteira);

    double calcularTotalInvestidoPorAtivo(Ativo ativo);

    double calcularRendimentoAtivo(Integer idCarteira, Ativo ativo, LocalDate dataInicial, LocalDate dataFinal);

    void consultarAtivoNaCarteira(Integer idCarteira, Ativo ativo);

    void listarAtivosNaCarteira(Integer idCarteira);

    void removerAtivoCarteira(Integer idCarteira, Ativo ativo);
}
