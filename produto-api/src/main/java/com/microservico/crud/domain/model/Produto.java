package com.microservico.crud.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "estoque",nullable = false)
    private Integer estoque;

    @Column(name = "preco", nullable = false)
    private BigDecimal preco;
}
