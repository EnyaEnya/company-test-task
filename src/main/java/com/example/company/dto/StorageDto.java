package com.example.company.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StorageDto extends AbstractDto {

    private Long materialId;
    private Long quantity;
}
