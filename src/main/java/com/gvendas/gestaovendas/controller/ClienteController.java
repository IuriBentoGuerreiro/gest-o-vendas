package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.dto.cliente.ClienteReqestDTO;
import com.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
import com.gvendas.gestaovendas.model.Cliente;
import com.gvendas.gestaovendas.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Pegar cliente por Id", operationId = "pegarClientePorId")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> pegarClientePorId(@PathVariable Long id){
        Optional<Cliente> cliente = clienteService.pegarClientePorId(id);
        return cliente.isPresent() ? ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO
                (cliente.get())) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar clientes", operationId = "listarClientes")
    @GetMapping
    public List<ClienteResponseDTO> listarClientes(){
        return clienteService.listarClientes().stream().map(cliente -> ClienteResponseDTO.
                        converterParaClienteDTO(cliente)).collect(Collectors.toList());
    }

    @Operation(summary = "Atualizar clientes", operationId = "atualizarClientes")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteReqestDTO cliente){
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente.converterParaEntidade());
        return ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO(clienteAtualizado));
    }

    @Operation(summary = "Salvar cliente", operationId = "salvarCliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvarCliente(@RequestBody @Valid ClienteReqestDTO clienteDTO){
        Cliente clienteSalvo = clienteService.salvarCliente(clienteDTO.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body
                (ClienteResponseDTO.converterParaClienteDTO(clienteSalvo));
    }
    @Operation(summary = "Deletar cliente", operationId = "DeletarCliente")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }
}
