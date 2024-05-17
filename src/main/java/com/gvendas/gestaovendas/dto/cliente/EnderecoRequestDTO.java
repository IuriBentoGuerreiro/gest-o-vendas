package com.gvendas.gestaovendas.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EnderecoRequestDTO {

    @Schema(description = "Logradouro")
    @NotBlank(message = "Logradouro")
    private String logradouro;

    @Schema(description = "Numero")
    @NotNull(message = "Numero")
    private Integer numero;

    @Schema(description = "Complemento")
    private String complemento;

    @Schema(description = "Bairro")
    @NotBlank(message = "Bairro")
    private String bairro;

    @Schema(description = "Cep")
    @NotBlank(message = "Cep")
    @Pattern(regexp = "[\\d]{5}-[\\d]{3}", message = "Cep")
    private String cep;

    @Schema(description = "Cidade")
    @NotBlank(message = "Cidade")
    private String cidade;

    @Schema(description = "Estado")
    @NotBlank(message = "Estado")
    private String estado;
}
