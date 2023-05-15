package br.edu.ifsp.rendafixa.domain.usescases.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {

    private List<Error> errors = new ArrayList<>();

    public void addError(String message){
        addError(message, null);
    }

    public void addError(String message, Exception e){
        errors.add(new Error(message, e));
    }

    public boolean isCorrect(){
        return errors.isEmpty();
    }

    public boolean hasErros(){
        return ! isCorrect();
    }

    private static class Error{
        String message;
        Exception cause;

        public Error(String message, Exception cause) {
            this.message = message;
            this.cause = cause;
        }
    }
    //percorre o vetor de erros e pra cada erro que encontrar vai mapear para String, pega todas as Strings
    //e concatena numa única string - utiliza API stream do java
    public String errorMessage(){
        return errors.stream()
                .map(e -> e.message)
                .collect(Collectors.joining(" ,"));
    }
}