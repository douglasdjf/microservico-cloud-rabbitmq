package com.pagamento.domain.service;

import com.pagamento.domain.model.Produto;
import com.pagamento.domain.repository.ProdutoRepository;
import com.pagamento.dto.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProdutoDTO> findAll(Pageable pageable) {
        var page= produtoRepository.findAll(pageable);
        return page.map(produto -> modelMapper.map(produto,ProdutoDTO.class));
    }


    public ProdutoDTO save(ProdutoDTO produto) {
        Produto produtoConvert = this.modelMapper.map(produto, Produto.class);
        Produto produtoSalvo = this.produtoRepository.save(produtoConvert);
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
