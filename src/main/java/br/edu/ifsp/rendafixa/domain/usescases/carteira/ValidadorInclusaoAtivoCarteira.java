package br.edu.ifsp.rendafixa.domain.usescases.carteira;

import br.edu.ifsp.rendafixa.domain.entities.carteira.Carteira;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

import java.time.LocalDate;

public class ValidadorInclusaoAtivoCarteira extends Validator<Carteira> {
    public Notification validate(Carteira carteira){
        Notification notification = new Notification();

        LocalDate dataAtual = LocalDate.now();
        int compara = carteira.getDataCompra().compareTo(dataAtual);

        if (carteira == null) {
            notification.addError("Carteira nula!");
            return notification;
        }
        if (compara >0)
            notification.addError("Data da compra é posterior à data atual!");

        return notification;
    }
}
