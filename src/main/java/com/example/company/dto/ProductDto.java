package com.example.company.dto;


import com.example.company.entity.Material;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.SortedSet;

@Data
@NoArgsConstructor
public class ProductDto extends AbstractDto {

    private String name;
    private Long price;
    private Long measureId;
    private SortedSet<Material> materials;
}
