package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import com.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import com.gvendas.gestaovendas.model.Categoria;
import com.gvendas.gestaovendas.model.Produto;
import com.gvendas.gestaovendas.service.ProdutoService;
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

@Tag(name = "Produto")
@RestController
@RequestMapping("/categoria{idCategoria}/produto/")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Pegar produto por Id", operationId = "pegarProdutoPorId")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> pegarProdutoPorId(@PathVariable Long id,@PathVariable Long idCategoria){
        Optional<Produto> produto = produtoService.pegarProdutoPorId(id, idCategoria);
        return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.
                converterParaProdutoDTO(produto.get())) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Listar produtos", operationId = "listarProdutos")
    @GetMapping
    public List<ProdutoResponseDTO> listarProdutos(Long idCategoria, Categoria categoria){
        return produtoService.listarProdutos(idCategoria).stream().
                map(produto -> ProdutoResponseDTO.converterParaProdutoDTO(produto))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Salvar produto", operationId = "salvarProduto")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvarProduto(@PathVariable Long idCategoria, @Valid @RequestBody ProdutoRequestDTO produto){
        Produto produtoSalvo = produtoService.salvarProduto(idCategoria, produto.converterParaEntidade(idCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converterParaProdutoDTO(produtoSalvo));
    }

    @Operation(summary = "Atualizar produto", operationId = "atualizarProduto")
    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long idProduto, @PathVariable Long idCategoria, @Valid
    @RequestBody ProdutoRequestDTO produto){
        Produto produto1 = produtoService.atualizarProduto(idCategoria, idProduto, produto.converterParaEntidade(idCategoria, idProduto));
        return ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produto1));
    }

    @Operation(summary = "Deletar produto", operationId = "deletarProduto")
    @DeleteMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long idCategoria, @PathVariable Long idProduto){
        produtoService.deletarProduto(idCategoria, idProduto);
    }
}
