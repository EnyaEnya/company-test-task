package com.example.company.dto;


import com.example.company.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductDto extends AbstractDto {

    private String name;
    private Long price;
    private Long measureId;
    private List<MaterialDto> materials;

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setMeasureId(product.getMeasureId());
        this.setMaterials(product.getMaterials().stream().map(MaterialDto::new).collect(Collectors.toList()));
    }
}
