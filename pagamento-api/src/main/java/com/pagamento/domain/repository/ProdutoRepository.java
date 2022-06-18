package com.pagamento.domain.repository;

import com.pagamento.domain.model.Produto;
import com.pagamento.domain.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
