package com.example.company.service;

import com.example.company.dto.MaterialDto;
import com.example.company.entity.Material;
import com.example.company.repository.MaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    public Material create(MaterialDto materialDto) {
        Material material = new Material();
        return updateFromDtoAndSave(material, materialDto);
    }

    private Material updateFromDtoAndSave(Material material, MaterialDto materialDto) {
        material.setName(materialDto.getName());
        material.setPrice(materialDto.getPrice());
        material.setMeasureId(materialDto.getMeasureId());
        material.setPrice(materialDto.getPrice());
        return materialRepository.save(material);
    }

}
