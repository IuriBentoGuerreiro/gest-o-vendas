package com.gvendas.gestaovendas.exceptions;


public class RegraNegocioExeption extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public RegraNegocioExeption(String mensagem) {
        super(mensagem);
    }
}
