package com.pagamento.domain.repository;

import com.pagamento.domain.model.ProdutoVenda;
import com.pagamento.domain.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda,Long> {
}
