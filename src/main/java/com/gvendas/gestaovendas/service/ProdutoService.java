package com.gvendas.gestaovendas.service;

import com.gvendas.gestaovendas.exceptions.RegraNegocioExeption;
import com.gvendas.gestaovendas.model.Produto;
import com.gvendas.gestaovendas.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Optional<Produto> pegarProdutoPorId(Long id, Long idCategoria){
        return produtoRepository.PegarProdutoPorId(id, idCategoria);
    }

    public List<Produto> listarProdutos(Long idCategoria){
        return produtoRepository.findByCategoriaId(idCategoria);
    }

    public Produto salvarProduto(Long idCategoria, Produto produto){
        validarCategoriaExiste(idCategoria);
        validarProdutoDuplicado(produto);
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(Long idCategoria, Long idProduto,Produto produto){
        Produto produtoSalvar = validarProdutoExiste(idProduto, idCategoria);
        validarCategoriaExiste(idCategoria);
        validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvar,"id");
        return produtoRepository.save(produtoSalvar);
    }

    public void deletarProduto(Long idCategoria, Long idProduto){
        Produto produto = validarProdutoExiste(idProduto, idCategoria);
        produtoRepository.delete(produto);
    }

    protected void atualizarQuantidadeEmEstoque(Produto produto){
        produtoRepository.save(produto);
    }

    protected Produto validarProdutoExiste(Long idProduto){
        Optional<Produto> produto =produtoRepository.findById(idProduto);
        if (produto.isEmpty()){
            throw new RegraNegocioExeption(String.format("O produto %s não exiate", idProduto));
        }
        return produto.get();
    }

    private Produto validarProdutoExiste(Long idProduto, Long idCategoria){
        Optional<Produto> produto = pegarProdutoPorId(idProduto, idCategoria);
        if (produto.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return produto.get();
    }

    private void validarProdutoDuplicado(Produto produto){
        Optional <Produto> produtoPorDescricao = produtoRepository.findByCategoriaIdAndDescricao(produto.getCategoria().getId(), produto.getDescricao());
        if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getId() != produto.getId()){
            throw new RegraNegocioExeption(String.format("O produto %s já está cadastrado", produto.getDescricao()));
        }
    }

    private void validarCategoriaExiste(Long idCategoria){
        if (idCategoria == null){
            throw new RegraNegocioExeption("A categoria não pode ser nula");
        }
        if (categoriaService.pegarPorid(idCategoria).isEmpty()){
            throw new RegraNegocioExeption(String.format("A categoria de código %s não existe no cadastro", idCategoria));
        }
    }


}
