package com.pagamento.domain.service;

import com.pagamento.domain.model.Produto;
import com.pagamento.domain.model.ProdutoVenda;
import com.pagamento.domain.model.Venda;
import com.pagamento.domain.repository.ProdutoRepository;
import com.pagamento.domain.repository.ProdutoVendaRepository;
import com.pagamento.domain.repository.VendaRepository;
import com.pagamento.dto.ProdutoDTO;
import com.pagamento.dto.VendaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<VendaDTO> findAll(Pageable pageable) {
        var page= vendaRepository.findAll(pageable);
        return page.map(produto -> modelMapper.map(produto,VendaDTO.class));
    }


    public VendaDTO save(VendaDTO vendaDTO) {
        Venda vendaConvert = this.modelMapper.map(vendaDTO, Venda.class);
        Venda vendaSalvo = this.vendaRepository.save(vendaConvert);

        List<ProdutoVenda> produtoSalvos = new ArrayList<>();
        vendaDTO.getProdutos().forEach(p ->{
            ProdutoVenda pv = modelMapper.map(p, ProdutoVenda.class);
            pv.setVenda(vendaSalvo);
            produtoSalvos.add(produtoVendaRepository.save(pv));
        });
        vendaSalvo.setProdutos(produtoSalvos);
        return this.modelMapper.map(vendaSalvo,VendaDTO.class) ;
    }


    public VendaDTO findById(Long id) {
        Venda venda = this.vendaRepository.findById(id).orElseThrow( () -> new RuntimeException("Id " + id + " não existe!"));
        return  modelMapper.map(venda,VendaDTO.class);
    }


    public void deleteById(Long id) {
        this.vendaRepository.deleteById(id);
    }


    public VendaDTO update(VendaDTO object) {
        var venda = this.vendaRepository.save(modelMapper.map(object,Venda.class));
        return modelMapper.map(venda,VendaDTO.class) ;
    }
}
