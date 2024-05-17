package com.gvendas.gestaovendas.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoResponseDTO {

    @Schema(description = "Logradouro")
    private String logradouro;

    @Schema(description = "Numero")
    private Integer numero;

    @Schema(description = "Complemento")
    private String complemento;

    @Schema(description = "Bairro")
    private String bairro;

    @Schema(description = "Cep")
    private String cep;

    @Column(name = "cidade")
    @Schema(description = "Cidade")
    private String cidade;

    @Column(name = "estado")
    @Schema(description = "Estado")
    private String estado;
}
