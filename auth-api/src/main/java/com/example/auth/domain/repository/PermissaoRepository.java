package com.example.auth.domain.repository;

import com.example.auth.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao,Long> {

    Optional<Permissao> findByDescricao(String descricao);
}
