package com.example.auth.domain.service;

import com.example.auth.domain.model.Permissao;
import com.example.auth.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao findByDescricao(String descricao){
        return permissaoRepository.findByDescricao(descricao).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
