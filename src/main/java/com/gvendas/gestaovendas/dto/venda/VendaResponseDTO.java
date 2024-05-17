package com.gvendas.gestaovendas.dto.venda;

import com.gvendas.gestaovendas.models.ItemVenda;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class VendaResponseDTO {

    @Schema(description = "Id")
    private Long id;

    @Schema(description = "Data")
    private LocalDate data;

    @Schema(description = "Itens da venda")
    private List<ItemVendaResponseDTO> itemVendaResponseDTOS;
}
