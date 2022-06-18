package com.pagamento.controller;

import com.pagamento.domain.service.VendaService;
import com.pagamento.dto.VendaDTO;
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

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private PagedResourcesAssembler<VendaDTO> assembler;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAll(@RequestParam(value = "page",defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "5") int limit,
                                     @RequestParam(value = "direction",defaultValue = "asc") String direction,
                                     @RequestParam(value = "orderBy",defaultValue = "data") String orderBy){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page,limit,Sort.by(sortDirection,orderBy));
        Page<VendaDTO> vendaDTOPage = this.vendaService.findAll(pageable);

        vendaDTOPage.stream()
                .forEach(venda -> venda.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).findById(venda.getId())).withSelfRel()));

        PagedModel<EntityModel<VendaDTO>> pagedModel = assembler.toModel(vendaDTOPage);

        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public VendaDTO findById(@PathVariable("id") Long id){
        VendaDTO produtoDTO = vendaService.findById(id);
        produtoDTO.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).findById(id)).withSelfRel());
        return produtoDTO;
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public ResponseEntity<VendaDTO>  save(@RequestBody VendaDTO vendaDTO){
        VendaDTO produtSave = vendaService.save(vendaDTO);
        produtSave.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).findById(produtSave.getId())).withSelfRel())
                .add((WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).findById(produtSave.getId())).withRel("delete")))
                .add((WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).findById(produtSave.getId())).withRel("update")));
        return ResponseEntity.ok(produtSave);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        vendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
