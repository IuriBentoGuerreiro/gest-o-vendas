package com.gvendas.gestaovendas.repositorys;

import com.gvendas.gestaovendas.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List <Produto> findByCategoriaId(Long idCategoria);

    @Query("SELECT p FROM Produto p WHERE p.id = :id AND p.categoria.id = :idCategoria")
    Optional<Produto> PegarProdutoPorId(Long id, Long idCategoria);

    Optional<Produto> findByCategoriaIdAndDescricao(Long idCategoria, String descricao);
}
