package com.gvendas.gestaovendas.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "categoria")
@Data
@EqualsAndHashCode
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    public Categoria(){

    }
    public Categoria(Long id){
        this.id = id;
    }

    public Categoria(String nome){
        this.nome = nome;
    }

    public Categoria(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

}
