package com.gvendas.gestaovendas.dto.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VendaRequestDTO {

    @Schema(description = "Data")
    @NotNull(message = "Data")
    private LocalDate data;

    @Schema(description = "Itens da venda")
    @NotNull(message = "Itens venda")
    private List<ItemVendaRequestDTO> itemVendaDTO;
}
