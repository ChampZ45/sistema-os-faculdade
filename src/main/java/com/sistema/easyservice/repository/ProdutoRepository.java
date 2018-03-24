package com.sistema.easyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.easyservice.model.Produto;
import com.sistema.easyservice.repository.queries.ProdutoQueries;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, ProdutoQueries {

}
