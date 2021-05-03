package com.example.company.controller;

import com.example.company.dto.StorageItemDto;
import com.example.company.exception.ResourceNotFoundException;
import com.example.company.logging.Log;
import com.example.company.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/storage")
@AllArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping
    public List<StorageItemDto> getAllStorageData() {
        return storageService.getAllStorageData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StorageItemDto> getStorageItemById(@PathVariable(value = "id") Long storageItemId) throws ResourceNotFoundException {
        return storageService.getItemById(storageItemId);
    }

    @Log
    @PostMapping
    public StorageItemDto createStorageItem(@Valid @RequestBody StorageItemDto storageItemDto) {
        return storageService.createStorageItem(storageItemDto);
    }

    @Log
    @PutMapping("/{id}")
    public ResponseEntity<StorageItemDto> updateStorageItem(@PathVariable(value = "id") Long storageItemId,
                                                            @Valid @RequestBody StorageItemDto storageItemDto) throws ResourceNotFoundException {
        return storageService.updateStorageItem(storageItemId, storageItemDto);
    }

    @Log
    @DeleteMapping("/{id}")
    public void deleteStorageItem(@PathVariable(value = "id") Long storageItemId) throws ResourceNotFoundException {
        storageService.deleteStorageItem(storageItemId);
    }
}
