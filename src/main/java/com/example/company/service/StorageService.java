package com.example.company.service;

import com.example.company.dto.StorageDto;
import com.example.company.entity.StorageItem;
import com.example.company.repository.MaterialRepository;
import com.example.company.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    private final MaterialRepository materialRepository;

    @Transactional
    public StorageItem create(StorageDto storageDto) {
        StorageItem storageItem = storageRepository.findById(storageDto.getMaterialId()).orElseGet(StorageItem::new);
        return updateFromDtoAndSave(storageItem, storageDto);
    }

    private StorageItem updateFromDtoAndSave(StorageItem storageItem, StorageDto storageDto) {
        storageItem.setMaterial(materialRepository.getOne(storageDto.getMaterialId()));
        storageItem.setQuantity(storageDto.getQuantity());
        StorageItem save = storageRepository.save(storageItem);
        Hibernate.initialize(save.getMaterial());
        return save;
    }
}
