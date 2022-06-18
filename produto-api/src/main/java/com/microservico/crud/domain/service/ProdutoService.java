package com.microservico.crud.domain.service;

import com.microservico.crud.domain.model.Produto;
import com.microservico.crud.domain.repository.ProdutoRepository;
import com.microservico.crud.dto.ProdutoDTO;
import com.microservico.crud.message.ProdutoSendMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoSendMessage produtoSendMessage;

    @Autowired
    private ModelMapper modelMapper;


    public Page<ProdutoDTO> findAll(Pageable pageable) {
       var page= produtoRepository.findAll(pageable);
        return page.map(produto -> modelMapper.map(produto,ProdutoDTO.class));
    }

    public ProdutoDTO save(ProdutoDTO produto) {
        Produto produtoConvert = this.modelMapper.map(produto, Produto.class);
        Produto produtoSalvo = this.produtoRepository.save(produtoConvert);
        ProdutoDTO produtoDTOSalvo = this.modelMapper.map(produtoSalvo, ProdutoDTO.class);
        produtoSendMessage.sendMessage(produtoDTOSalvo);
        return this.modelMapper.map(produtoSalvo,ProdutoDTO.class) ;
    }


    public ProdutoDTO findById(Long id) {
        Produto produto = this.produtoRepository.findById(id).orElseThrow( () -> new RuntimeException("Id " + id + " não existe!"));
      return  modelMapper.map(produto,ProdutoDTO.class);
    }


    public void deleteById(Long id) {
        this.produtoRepository.deleteById(id);
    }


    public ProdutoDTO update(ProdutoDTO object) {
       var produto = this.produtoRepository.save(modelMapper.map(object,Produto.class));
        return modelMapper.map(produto,ProdutoDTO.class) ;
    }
}
