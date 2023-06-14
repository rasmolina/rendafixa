package br.edu.ifsp.rendafixa.domain.usescases.ativos;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.entities.ativos.CategoriaAtivo;
import br.edu.ifsp.rendafixa.domain.usescases.utils.DAO;

import java.time.LocalDate;
import java.util.Optional;

public interface AtivoDAO extends DAO<Ativo, Integer> {
    //Métodos adicionais: busca por categoria de ativo, pelo nome ou pela data de vencimento
    Optional<Ativo> buscaPorCategoria(CategoriaAtivo categoriaAtivo);
    Optional<Ativo> buscaPorNome(String nome);
    Optional<Ativo> buscaPorVencimento(LocalDate vencimento);
    Ativo buscar(int id);
}
