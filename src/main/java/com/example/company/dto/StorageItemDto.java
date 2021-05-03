package com.example.company.dto;

import com.example.company.entity.StorageItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StorageItemDto extends AbstractDto {

    private Long materialId;
    private Long quantity;
    private MaterialDto materialDto;

    public StorageItemDto(StorageItem storageItem) {
        this.id = storageItem.getId();
        this.materialId = storageItem.getId();
        this.quantity = storageItem.getQuantity();
        this.materialDto = new MaterialDto(storageItem.getMaterial());
    }
}
