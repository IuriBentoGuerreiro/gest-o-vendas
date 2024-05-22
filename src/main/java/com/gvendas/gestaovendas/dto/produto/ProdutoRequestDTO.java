package com.gvendas.gestaovendas.dto.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
public class ProdutoRequestDTO {

    @Schema(description = "Descrição")
    @Length(min = 3, max = 100)
    @Column(name = "descricao")
    private String descricao;

    @Schema(description = "Quantidade")
    @Column(name = "quantidade")
    private Integer quantidade;

    @Schema(description = "Preço custo")
    @Column(name = "precoCusto")
    private BigDecimal precoCusto;

    @Schema(description = "Preço venda")
    @Column(name = "precoVenda")
    private BigDecimal precoVenda;

    @Schema(description = "Observação")
    @Length(max = 500, message = "Observação")
    @Column(name = "observacao")
    private String observacao;

    public Produto converterParaEntidade(Long idCategoria){
        return new Produto(descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(idCategoria));
    }

    public Produto converterParaEntidade(Long idCategoria, Long idProduto){
        return new Produto(idProduto, descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(idCategoria));
    }
}
