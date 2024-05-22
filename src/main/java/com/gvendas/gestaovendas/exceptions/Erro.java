package com.gvendas.gestaovendas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Erro {

    private String msgUsuario;
    private String msgDesenvolvedor;

}
