package com.pagamento.domain.repository;

import com.pagamento.domain.model.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda,Long> {
}
