package com.pagamento.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable {

    private Long id;
    private Integer estoque;
}
