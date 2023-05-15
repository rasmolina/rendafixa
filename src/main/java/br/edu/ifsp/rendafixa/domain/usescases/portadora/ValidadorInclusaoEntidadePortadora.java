package br.edu.ifsp.rendafixa.domain.usescases.portadora;

import br.edu.ifsp.rendafixa.domain.entities.portadora.Portadora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

public class ValidadorInclusaoEntidadePortadora extends Validator<Portadora> {
    public Notification validate(Portadora portadora){
        Notification notification = new Notification();

        if (portadora == null) {
            notification.addError("Portadora nula!");
            return notification;
        }
        if (nuloOuVazio(portadora.getNome()))
            notification.addError("Nome da portadora é nula ou vazia!");
        if (nuloOuVazio(portadora.getSigla()))
            notification.addError("Sigla da portadora é nula ou vazia!");

        return notification;
    }
}
