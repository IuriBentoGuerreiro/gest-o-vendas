package com.gvendas.gestaovendas.dto.categoria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CategoriaResponseDTO {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Nome")
    private String nome;

    public CategoriaResponseDTO(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public static CategoriaResponseDTO converterParaCategoriaDTO(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }
}
