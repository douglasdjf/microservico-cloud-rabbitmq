package com.pagamento.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO extends RepresentationModel<VendaDTO> implements Serializable {

    private Long id;
    private Date data;
    private BigDecimal valorTotal;
    private List<ProdutoVendaDTO> produtos;
}
