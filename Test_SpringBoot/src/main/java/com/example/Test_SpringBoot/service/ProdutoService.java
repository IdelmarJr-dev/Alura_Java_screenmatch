package com.example.Test_SpringBoot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Test_SpringBoot.model.Produto;
import com.example.Test_SpringBoot.repository.ProdutoRepository;
import com.exceptions.RecursoNaoEncontradoException;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID "+id+" não encontrado."));
    }

    public Produto salveProduto(Produto Produto) {
        return produtoRepository.save(Produto);
    }

    public void deleteProduto(Long id) {

        if(!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto com ID "+id+" não encontrado.");
        }
        produtoRepository.deleteById(id);
    }
}
