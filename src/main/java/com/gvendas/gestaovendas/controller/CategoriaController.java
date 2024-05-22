package com.gvendas.gestaovendas.controller;

import com.gvendas.gestaovendas.dto.categoria.CategoriaRequestDTO;
import com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import com.gvendas.gestaovendas.model.Categoria;
import com.gvendas.gestaovendas.service.CategoriaService;
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

@Tag(name = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Listar categorias",operationId = "listarTodas")
    @GetMapping
    public List<CategoriaResponseDTO> listarTodas(){
        return categoriaService.listarTodas().stream().map(categoria ->
                CategoriaResponseDTO.converterParaCategoriaDTO(categoria)).
                collect(Collectors.toList());
    }

    @Operation(summary = "Pegar categoria por Id", operationId = "pegarPorId")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> pegarPorId(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaService.pegarPorid(id);
        return categoria.isPresent() ? ResponseEntity.ok(CategoriaResponseDTO.
                converterParaCategoriaDTO(categoria.get())) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Criar nova categoria", operationId = "salvarCategoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO>salvarCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaDTO){
        Categoria categoriaSalva = categoriaService.salvarCategoria(categoriaDTO.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converterParaCategoriaDTO(categoriaSalva));
    }

    @Operation(summary = "Deletar categoria por Id", operationId = "deletarCategoria")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCategoria(@PathVariable Long id){
        categoriaService.deletarCategoria(id);
    }

    @Operation(summary = "Atualizar categoria", operationId = "atualizarCategoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(@Valid @RequestBody CategoriaRequestDTO categoriaDTO, @PathVariable Long id){
        Categoria categoria = categoriaService.atualizarCategoria(id, categoriaDTO.converterParaEntidade(id));
        return ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoria));
    }
}
