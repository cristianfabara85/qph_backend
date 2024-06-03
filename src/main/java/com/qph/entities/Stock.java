package com.qph.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stock",schema="public")
@Getter
@Setter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockid")
    private Long stockId;

    @Column(name = "productoid")
    private Long productoId;

    @Column(name = "entradas")
    private Long entradas;

    @Column(name = "salidas")
    private Long salidas;

    @Column(name = "disponible")
    private Long disponible;

    public Long getStockId() {
        return stockId;
    }

}
