package com.example.company.service;

import com.example.company.dto.StorageItemDto;
import com.example.company.entity.Material;
import com.example.company.entity.StorageItem;
import com.example.company.exception.ResourceNotFoundException;
import com.example.company.repository.MaterialRepository;
import com.example.company.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    private final MaterialRepository materialRepository;

    @Transactional
    public StorageItemDto createStorageItem(StorageItemDto storageItemDto) {
        StorageItem storageItem = storageRepository.findById(storageItemDto.getMaterialId()).orElseGet(StorageItem::new);
        return updateFromDtoAndSave(storageItem, storageItemDto);
    }

    @Transactional
    public List<StorageItemDto> getAllStorageData() {
        return storageRepository.findAll().stream().map(StorageItemDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<StorageItemDto> getItemById(Long itemId) throws ResourceNotFoundException {
        StorageItem storageItem = storageRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Storage item not found for this id :: " + itemId));
        StorageItemDto storageItemDto = new StorageItemDto(storageItem);
        return ResponseEntity.ok().body(storageItemDto);
    }

    @Transactional
    public ResponseEntity<StorageItemDto> updateStorageItem(Long storageItemId, StorageItemDto storageItemDto) throws ResourceNotFoundException {
        StorageItem storageItem = storageRepository.findById(storageItemId)
                .orElseThrow(() -> new ResourceNotFoundException("StorageItem not found for this id :: " + storageItemId));
        Material material = materialRepository.findById(storageItemDto.getMaterialId())
                .orElseThrow(() -> new ResourceNotFoundException("Material not found for this id :: " + storageItemDto.getMaterialId()));
        storageItem.setMaterial(material);
        storageItem.setQuantity(storageItemDto.getQuantity());
        final StorageItem updatedStorageItem = storageRepository.save(storageItem);
        StorageItemDto updatedStorageItemDto = new StorageItemDto(updatedStorageItem);
        return ResponseEntity.ok(updatedStorageItemDto);
    }

    @Transactional
    public void deleteStorageItem(Long storageItemId) throws ResourceNotFoundException {
        StorageItem storageItem = storageRepository.findById(storageItemId)
                .orElseThrow(() -> new ResourceNotFoundException("StorageItem not found for this id :: " + storageItemId));
        storageRepository.delete(storageItem);
    }

    private StorageItemDto updateFromDtoAndSave(StorageItem storageItem, StorageItemDto storageItemDto) {
        storageItem.setMaterial(materialRepository.getOne(storageItemDto.getMaterialId()));
        storageItem.setQuantity(storageItemDto.getQuantity());
        return new StorageItemDto(storageRepository.save(storageItem));
    }
}
