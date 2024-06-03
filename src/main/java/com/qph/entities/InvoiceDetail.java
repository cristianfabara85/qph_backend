package com.qph.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "factdet",schema="public")
@Getter
@Setter
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factcabid", nullable = false)
    @JsonBackReference
    private Invoice invoice;

    @Column(name = "productoid")
    private Long productId;

    @Column(name = "cantidad")
    private Long quantity;

    @Column(name = "precio")
    private Double price;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "iva")
    private Double tax;

    @Column(name = "total")
    private Double total;

}
