package com.pagamento.domain.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data")
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date data;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = {CascadeType.REFRESH})
    private List<ProdutoVenda> produtos;
}
