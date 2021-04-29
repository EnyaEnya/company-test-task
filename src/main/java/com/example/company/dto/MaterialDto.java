package com.example.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDto extends AbstractDto {

    private String name;
    private Long price;
    private Long measureId;
}
