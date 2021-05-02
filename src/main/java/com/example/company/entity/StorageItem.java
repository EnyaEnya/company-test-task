package com.example.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "storage_item")
public class StorageItem {

    @Id
    @Column(name = "material_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "material_id")
    private Material material;

    private Long quantity;
}
