package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.models.Cliente;
import com.gvendas.gestaovendas.models.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ClienteReqestDTO {

    @Schema(description = "Nome")
    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;

    @Schema(description = "Telefone")
    @Pattern(regexp = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}", message = "Telefone")
    private String telefone;

    @Schema(description = "Ativo")
    @NotNull(message = "Ativo")
    private boolean ativo;

    @Schema(description = "Endereço")
    @NotNull(message = "Endereço")
    private EnderecoRequestDTO enderecoDoCliente;


    public Cliente converterParaEntidade(){
        Endereco endereco = new Endereco (enderecoDoCliente.getLogradouro(), enderecoDoCliente.getNumero(),
                enderecoDoCliente.getComplemento(), enderecoDoCliente.getBairro(), enderecoDoCliente.getCep(),
                enderecoDoCliente.getCidade(), enderecoDoCliente.getEstado());
        return new Cliente(nome, telefone, ativo ,endereco);
    }


    public Cliente converterParaEntidade(Long id){
        Endereco endereco = new Endereco (enderecoDoCliente.getLogradouro(), enderecoDoCliente.getNumero(),
                enderecoDoCliente.getComplemento(), enderecoDoCliente.getBairro(), enderecoDoCliente.getCep(),
                enderecoDoCliente.getCidade(), enderecoDoCliente.getEstado());
        return new Cliente(nome, telefone, ativo ,endereco);
    }
}
