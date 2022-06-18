package com.pagamento.domain.service;

import com.pagamento.domain.model.Produto;
import com.pagamento.domain.model.ProdutoVenda;
import com.pagamento.domain.model.Venda;
import com.pagamento.domain.repository.ProdutoVendaRepository;
import com.pagamento.dto.ProdutoDTO;
import com.pagamento.dto.ProdutoVendaDTO;
import com.pagamento.dto.VendaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoVendaService {

    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<ProdutoVendaDTO> findAll(Pageable pageable) {
        var page= produtoVendaRepository.findAll(pageable);
        return page.map(produto -> modelMapper.map(produto,ProdutoVendaDTO.class));
    }


    public ProdutoVendaDTO save(ProdutoVendaDTO produtoVenda) {
        ProdutoVenda produtoVendaConvert = this.modelMapper.map(produtoVenda, ProdutoVenda.class);
        ProdutoVenda produtoVendaSalvo = this.produtoVendaRepository.save(produtoVendaConvert);
        return this.modelMapper.map(produtoVendaSalvo,ProdutoVendaDTO.class) ;
    }

    public ProdutoVendaDTO findById(Long id) {
        ProdutoVenda produtoVenda = this.produtoVendaRepository.findById(id).orElseThrow( () -> new RuntimeException("Id " + id + " não existe!"));
        return  modelMapper.map(produtoVenda,ProdutoVendaDTO.class);
    }


    public void deleteById(Long id) {
        this.produtoVendaRepository.deleteById(id);
    }


    public ProdutoVendaDTO update(ProdutoVendaDTO object) {
        var venda = this.produtoVendaRepository.save(modelMapper.map(object,ProdutoVenda.class));
        return modelMapper.map(venda,ProdutoVendaDTO.class) ;
    }
}
