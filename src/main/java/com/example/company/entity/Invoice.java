package com.example.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice extends AbstractEntity {

    private Instant date;

    private Long amount;

    @Column(name = "client_name")
    private String clientName;

    private String status;

    private String type;

}
