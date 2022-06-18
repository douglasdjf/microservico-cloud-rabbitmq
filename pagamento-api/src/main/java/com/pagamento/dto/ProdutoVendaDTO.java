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
public class ProdutoVendaDTO extends RepresentationModel<ProdutoVendaDTO> implements Serializable {

    private Long id;
    private Long produtoId;
    private Integer quantidade;
}
