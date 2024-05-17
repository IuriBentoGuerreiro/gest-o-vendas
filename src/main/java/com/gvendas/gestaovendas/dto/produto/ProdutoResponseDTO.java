package com.gvendas.gestaovendas.dto.produto;

import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.gvendas.gestaovendas.models.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProdutoResponseDTO {

    @Schema(description = "Id")
    private Long id;
    private String descricao;
    @Schema(description = "Id")
    private Integer quantidade;
    @Schema(description = "Id")
    private BigDecimal precoCusto;
    @Schema(description = "Id")
    private BigDecimal precoVenda;
    @Schema(description = "Id")
    private String observacao;
    @Schema(description = "Id")
    private CategoriaResponseDTO categoria;

    public static ProdutoResponseDTO converterParaProdutoDTO(Produto produto){
        return new ProdutoResponseDTO(produto.getId(),
                produto.getDescricao(), produto.getQuantidade(),
                produto.getPrecoCusto(), produto.getPrecoVenda(),
                produto.getObservacao(), CategoriaResponseDTO.
                converterParaCategoriaDTO(produto.getCategoria()));
    }

}
