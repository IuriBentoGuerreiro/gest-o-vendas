package com.gvendas.gestaovendas.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CategoriaRequestDTO {

    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    @Schema(description = "Nome")
    private String nome;

    public Categoria converterParaEntidade(){
        return new Categoria(nome);
    }

    public Categoria converterParaEntidade(Long id){
        return new Categoria(id, nome);
    }
}
