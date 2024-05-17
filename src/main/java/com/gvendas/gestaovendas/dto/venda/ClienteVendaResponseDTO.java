package com.gvendas.gestaovendas.dto.venda;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClienteVendaResponseDTO {

    @Schema(description = "Nome cliente")
    private String nome;

    @Schema(description = "Venda")
    private List<VendaResponseDTO> vendaResponseDTOS;

}
