package com.example.Test_SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Test_SpringBoot.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
    
}
