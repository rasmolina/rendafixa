package br.edu.ifsp.rendafixa.domain.usescases.ativos;

import br.edu.ifsp.rendafixa.domain.entities.ativos.Ativo;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.time.LocalDate;

public class ValidadorInclusaoAtivo extends Validator<Ativo> {
    public Notification validate(Ativo ativo){
        Notification notification = new Notification();

        LocalDate dataAtual = LocalDate.now();
        LocalDate vencimento = ativo.getDataVencimento();

        if (ativo == null) {
            notification.addError("Ativo nulo!");
            return notification;
        }
        if (nuloOuVazio(ativo.getNome()))
            notification.addError("Nome do ativo é nulo ou vazio!");
        if (nuloOuVazio(ativo.getCategoriaAtivo().toString()))
            notification.addError("Categoria do ativo é nula ou vazia!");
        if (nuloOuVazio(ativo.getCategoriaRentabilidade().toString()))
            notification.addError("Categoria da rentabilidade é nula ou vazia!");
        if (vencimento.isBefore(dataAtual))
            notification.addError("Data de vencimento é anterior à data atual!");

        return notification;
    }
}
