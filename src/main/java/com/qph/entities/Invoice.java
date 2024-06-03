package com.qph.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "factcab",schema="public")
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "clienteid")
    private Long clientId;

    @Column(name = "fecharegistro")
    private Date invoiceDate;

    @Column(name = "totalfactura")
    private Double total;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InvoiceDetail> items;


    public Long getId() {
        return id;
    }


    public void setItems(List<InvoiceDetail> items) {
        this.items = items;
        if (items != null) {
            for (InvoiceDetail item : items) {
                item.setInvoice(this);
            }
        }
    }
}
