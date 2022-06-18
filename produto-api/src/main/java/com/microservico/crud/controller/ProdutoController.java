package com.microservico.crud.controller;

import com.microservico.crud.domain.model.Produto;
import com.microservico.crud.domain.service.ProdutoService;
import com.microservico.crud.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 *  Mock de dados
 *  <--- Link para gerar dados de Insert https://www.mockaroo.com/ -->
 *
 */

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PagedResourcesAssembler<ProdutoDTO> assembler;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAll(@RequestParam(value = "page",defaultValue = "0") int page,
                                  @RequestParam(value = "limit", defaultValue = "5") int limit,
                                  @RequestParam(value = "direction",defaultValue = "asc") String direction,
                                  @RequestParam(value = "orderBy",defaultValue = "nome") String orderBy){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page,limit,Sort.by(sortDirection,orderBy));
        Page<ProdutoDTO> produtoDTOPage = this.produtoService.findAll(pageable);

        produtoDTOPage.stream()
                .forEach(produto -> produto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProdutoController.class).findById(produto.getId())).withSelfRel()));

        PagedModel<EntityModel<ProdutoDTO>> pagedModel = assembler.toModel(produtoDTOPage);

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public ProdutoDTO findById(@PathVariable("id") Long id){
        ProdutoDTO produtoDTO = produtoService.findById(id);
        produtoDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProdutoController.class).findById(id)).withSelfRel());
        return produtoDTO;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public ResponseEntity<ProdutoDTO>  save(@RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO produtSave = produtoService.save(produtoDTO);
        produtSave.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProdutoController.class).findById(produtSave.getId())).withSelfRel())
                  .add((WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProdutoController.class).findById(produtSave.getId())).withRel("delete")))
                  .add((WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProdutoController.class).findById(produtSave.getId())).withRel("update")));
        return ResponseEntity.ok(produtSave);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
