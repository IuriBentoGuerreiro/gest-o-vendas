package com.gvendas.gestaovendas.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GestaoVendasExeptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final String CONSTANT_VALIDATION_NOT_NULL = "NotNull";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";
    private static final String CONSTANT_VALIDATION_PATTERN = "Pattern";
    private static final String CONSTANT_VALIDATION_MIN = "Min";




    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Erro> erros = gerarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros,headers,HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.getMessage() != null ? ex.getMessage() : "Detalhes adicionais indisponíveis";
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
    }
    @ExceptionHandler(RegraNegocioExeption.class)
    public ResponseEntity<Object> handlerRegraNegocioExeption (RegraNegocioExeption ex, WebRequest request){
        String msgUsuario = ex.getMessage();
        String msgDensevolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDensevolvedor));
        return handleExceptionInternal(ex, erros,new HttpHeaders(),HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> gerarListaDeErros(BindingResult bindingResult){
        List<Erro> erros = new ArrayList<Erro>();
        bindingResult.getFieldErrors().forEach(fildError -> {
            String msgUsuario = tratarMensagemDeErroParaUsuario(fildError);
            String msgDesenvolvedor = fildError.toString();
            erros.add(new Erro(msgUsuario, msgDesenvolvedor));
        });
        return erros;
    }

    private String tratarMensagemDeErroParaUsuario(FieldError fieldError){
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)){
            return fieldError.getDefaultMessage().concat(" é obrigatório.");
        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_NULL)){
            return fieldError.getDefaultMessage().concat(" é obrigatório.");
        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)){
            return  fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.",
                    fieldError.getArguments()[2], fieldError.getArguments()[1]));
        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_PATTERN)){
            return fieldError.getDefaultMessage().concat(" Formato de telefone inválido");
        }
        if (fieldError.getCode().equals(CONSTANT_VALIDATION_MIN)){
            return fieldError.getDefaultMessage().concat(String.format("Deve ser maior ou igual a $s",
                    fieldError.getArguments()[1]));
        }
        return fieldError.toString();
    }
}
