package com.gvendas.gestaovendas.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
@EqualsAndHashCode
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "precoCusto")
    private BigDecimal precoCusto;

    @Column(name = "precoVenda")
    private BigDecimal precoVenda;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private Categoria categoria;

    public Produto(){

    }
    public Produto(Long id){
        this.id = id;
    }

    public Produto(Long id,String descricao, Integer quantidade, BigDecimal precoCusto,
                   BigDecimal precoVenda, String observacao, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }
    public Produto(String descricao, Integer quantidade, BigDecimal precoCusto,
                   BigDecimal precoVenda, String observacao, Categoria categoria) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }
}
