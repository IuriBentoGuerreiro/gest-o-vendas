package com.gvendas.gestaovendas.dto.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemVendaRequestDTO {

    @Schema(description = "IdProduto")
    @NotNull(message = "Id do produto")
    private Long idProduto;

    @Schema(description = "quantidade")
    @NotNull(message = "Quantidade")
    @Min(value = 1, message = "Quantidade")
    private Integer quantidade;

    @Schema(description = "preço Vendido")
    @NotNull(message = "Preço vendido")
    private BigDecimal precoVendido;
}
