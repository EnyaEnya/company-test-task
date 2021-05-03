package com.example.company.dto;

import com.example.company.entity.Material;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDto extends AbstractDto {

    private String name;
    private Long price;
    private Long measureId;

    public MaterialDto(Material material) {
        this.id = material.getId();
        this.name = material.getName();
        this.price = material.getPrice();
        this.measureId = material.getMeasureId();
    }
}
