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
@Table(name = "compras",schema="public")
@Getter
@Setter
public class Purchase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compraid")
    private Long compraId;

    @Column(name = "productoid")
    private Long productoId;

    @Column(name = "proveedor")
    private String proveedor;

    @Column(name = "cantidad")
    private Long cantidad;

    @Column(name = "fecharegistro")
    private Date fechaRegistro;

    @Column(name = "preciounitario")
    private Double precioUnitario;

    @Column(name = "preciototal")
    private Double precioTotal;


    public Long getCompraId() {
        return compraId;
    }

}
