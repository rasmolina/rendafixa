package br.edu.ifsp.rendafixa.domain.usescases.utils;

import java.util.Collection;

public abstract class Validator<T> {
    public abstract Notification validate(T type);

    public static boolean nuloOuVazio(String string){
        return string == null || string.isEmpty();
    }

    public static boolean nuloOuVazio(Integer integer) { return integer == null || integer == 0;}

    public static boolean nuloOuVazio(Collection collection){
        return collection == null || collection.isEmpty();
    }

}