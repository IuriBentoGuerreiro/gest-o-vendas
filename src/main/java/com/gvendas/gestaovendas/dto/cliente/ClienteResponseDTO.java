package com.gvendas.gestaovendas.dto.cliente;

import com.gvendas.gestaovendas.models.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteResponseDTO {

    private final EnderecoResponseDTO enderecoDTO;

    @Schema(description = "Id")
    private Long id;
    @Schema(description = "Nome")
    private String nome;
    @Schema(description = "Telefone")
    private String telefone;
    @Schema(description = "Ativo")
    private boolean ativo;

    public ClienteResponseDTO(Long id, String nome, String telefone, boolean ativo, EnderecoResponseDTO enderecoDTO) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.enderecoDTO = enderecoDTO;
    }

    public static ClienteResponseDTO converterParaClienteDTO(Cliente cliente){
        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO (cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getNumero(), cliente.getEndereco().getComplemento(), cliente.getEndereco().getBairro(),
                cliente.getEndereco().getCep(), cliente.getEndereco().getCidade(),cliente.getEndereco().getEstado());
        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(),
                cliente.getTelefone(), cliente.isAtivo(), enderecoResponseDTO);
    }
}
