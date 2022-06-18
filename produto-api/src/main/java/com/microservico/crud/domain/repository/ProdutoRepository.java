package com.microservico.crud.domain.repository;

import com.microservico.crud.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto,Long> {
}
