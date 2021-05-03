package com.example.company.service;

import com.example.company.dto.MaterialDto;
import com.example.company.entity.Material;
import com.example.company.exception.ResourceNotFoundException;
import com.example.company.repository.MaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Transactional
    public List<MaterialDto> getAllMaterials() {
        return materialRepository.findAll().stream().map(MaterialDto::new).collect(Collectors.toList());
    }

    @Transactional
    public MaterialDto createMaterial(MaterialDto materialDto) {
        Material material = new Material();
        material.setName(materialDto.getName());
        material.setPrice(materialDto.getPrice());
        material.setMeasureId(materialDto.getMeasureId());
        material.setPrice(materialDto.getPrice());
        Material createdMaterial = materialRepository.save(material);
        return new MaterialDto(createdMaterial);
    }

    @Transactional
    public ResponseEntity<MaterialDto> getMaterialById(Long materialId) throws ResourceNotFoundException {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found for this id :: " + materialId));
        MaterialDto materialDto = new MaterialDto(material);
        return ResponseEntity.ok().body(materialDto);
    }

    @Transactional
    public ResponseEntity<MaterialDto> updateMaterial(Long materialId, MaterialDto materialDetails) throws ResourceNotFoundException {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found for this id :: " + materialId));
        material.setName(materialDetails.getName());
        material.setPrice(materialDetails.getPrice());
        material.setMeasureId(materialDetails.getMeasureId());
        final Material updatedMaterial = materialRepository.save(material);
        MaterialDto updatedMaterialDto = new MaterialDto(updatedMaterial);
        return ResponseEntity.ok(updatedMaterialDto);
    }

    @Transactional
    public void deleteMaterial(Long materialId) throws ResourceNotFoundException {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found for this id :: " + materialId));
        materialRepository.delete(material);
    }

}
