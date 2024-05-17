package com.gvendas.gestaovendas.dto.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ItemVendaResponseDTO {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Quantidade")
    private Integer quantidade;

    @Schema(description = "Preço vendido")
    private BigDecimal precoVendido;

    @Schema(description = "Id produto")
    private Long idProduto;

    @Schema(description = "Descrição preoduto")
    private String preodutoDescricao;


}
