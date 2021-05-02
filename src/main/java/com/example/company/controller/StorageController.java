package com.example.company.controller;

import com.example.company.dto.StorageDto;
import com.example.company.entity.StorageItem;
import com.example.company.repository.StorageRepository;
import com.example.company.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/storage")
@AllArgsConstructor
public class StorageController {

    private final StorageRepository storageRepository;

    private final StorageService storageService;

    @GetMapping
    public List<StorageItem> getAllMaterialsOnStorage() {
        return storageRepository.findAll();
    }

    @PostMapping
    public StorageItem createMaterial(@Valid @RequestBody StorageDto storageDto) {
        return storageService.create(storageDto);
    }
}
