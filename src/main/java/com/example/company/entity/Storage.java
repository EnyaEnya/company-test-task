package com.example.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "storage")
public class Storage extends AbstractEntity {

    @Column(name = "material_id")
    private Long materialId;

    private Long quantity;
}
