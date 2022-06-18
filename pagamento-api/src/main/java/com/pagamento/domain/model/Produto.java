package com.pagamento.domain.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    private Long id;

    @Column(name = "estoque")
    private Integer estoque;
}
