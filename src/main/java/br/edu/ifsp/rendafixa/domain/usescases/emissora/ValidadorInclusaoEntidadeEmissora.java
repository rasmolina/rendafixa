package br.edu.ifsp.rendafixa.domain.usescases.emissora;

import br.edu.ifsp.rendafixa.domain.entities.emissora.Emissora;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Notification;
import br.edu.ifsp.rendafixa.domain.usescases.utils.Validator;

public class ValidadorInclusaoEntidadeEmissora extends Validator<Emissora> {
    public Notification validate(Emissora emissora){
        Notification notification = new Notification();

        if (emissora == null) {
            notification.addError("Emissora nula!");
            return notification;
        }
        if (nuloOuVazio(emissora.getNome()))
            notification.addError("Nome da emissora é nula ou vazia!");
        if (nuloOuVazio(emissora.getSigla()))
            notification.addError("Sigla da emissora é nula ou vazia!");

        return notification;
    }
}
