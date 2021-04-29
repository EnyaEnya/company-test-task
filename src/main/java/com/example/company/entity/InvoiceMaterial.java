package com.example.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "invoice_material")
public class InvoiceMaterial extends AbstractEntity {

    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "material_id")
    private Long materialId;

    private Double quantity;

    private Long price;

}
