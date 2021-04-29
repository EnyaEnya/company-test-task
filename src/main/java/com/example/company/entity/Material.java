package com.example.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "material")
public class Material extends AbstractEntity implements Comparable<Material> {

    private String name;

    private Long price;

    @Column(name = "measure_id")
    private Long measureId;

    @Override
    public int compareTo(Material o) {
        return id.compareTo(o.id);
    }
}
