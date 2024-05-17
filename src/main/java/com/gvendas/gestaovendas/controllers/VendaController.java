package com.gvendas.gestaovendas.controllers;

import com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import com.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import com.gvendas.gestaovendas.models.Venda;
import com.gvendas.gestaovendas.services.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Venda")
@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Operation(summary = "Listar vendas por cliente", operationId = "ListarVendasPorCliente")
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> ListarVendasPorClientes(@PathVariable Long idCliente){
        return ResponseEntity.ok(vendaService.listarVendaPorCliente(idCliente));
    }

    @Operation(summary = "Listar vendas por id", operationId = "ListarVendasPorId")
    @GetMapping("/{idVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> ListarVendasPorId(@PathVariable Long idVenda){
        return ResponseEntity.ok(vendaService.listarVendaPorId(idVenda));
    }

    @Operation(summary = "Salvar venda", operationId = "SalvarVenda")
    @PostMapping("/cliente/{idCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> salvar(@PathVariable Long idCliente, @Valid @RequestBody VendaRequestDTO vendaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.salvar(idCliente, vendaDTO));
    }

    @Operation(summary = "Deletar venda", operationId = "DeletarVenda")
    @DeleteMapping("/{idVenda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long idVenda){
        vendaService.deletar(idVenda);
    }

}
