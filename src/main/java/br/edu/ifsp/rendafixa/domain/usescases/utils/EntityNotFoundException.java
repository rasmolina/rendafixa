package br.edu.ifsp.rendafixa.domain.usescases.utils;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
