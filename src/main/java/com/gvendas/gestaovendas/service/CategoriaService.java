package com.gvendas.gestaovendas.service;

import com.gvendas.gestaovendas.exceptions.RegraNegocioExeption;
import com.gvendas.gestaovendas.model.Categoria;
import com.gvendas.gestaovendas.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> pegarPorid(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria salvarCategoria(Categoria categoria) {
        validarCategoriaDuplicada(categoria);
        return categoriaRepository.save(categoria);
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria atualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaSalvar = validarCategoriaExiste(id);
        validarCategoriaDuplicada(categoria);
        BeanUtils.copyProperties(categoria,categoriaSalvar, "id");
        return categoriaRepository.save(categoriaSalvar);
    }

    public Categoria validarCategoriaExiste(Long id) {
        Optional<Categoria> categoria = pegarPorid(id);
        if (categoria.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return categoria.get();
    }

    private void validarCategoriaDuplicada(Categoria categoria){
        Categoria categoriaEncontrada = categoriaRepository.findByNome(categoria.getNome());
        if (categoriaEncontrada  != null && categoriaEncontrada.getId() != categoria.getId()){
            throw new RegraNegocioExeption(String.format("A categoria %s ja existe", categoria.getNome().toUpperCase()));
        }
    }
}