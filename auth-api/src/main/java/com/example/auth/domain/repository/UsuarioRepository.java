package com.example.auth.domain.repository;

import com.example.auth.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

   Optional<Usuario> findByUserName(String userName);
}
