package com.microservico.crud.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable {

    private Long id;
    private String nome;
    private Integer estoque;
    private BigDecimal preco;
}
