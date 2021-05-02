package com.example.company.controller;

import com.example.company.dto.MaterialDto;
import com.example.company.entity.Material;
import com.example.company.exception.ResourceNotFoundException;
import com.example.company.repository.MaterialRepository;
import com.example.company.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class MaterialController {
    private final MaterialRepository materialRepository;

    private final MaterialService materialService;

    @GetMapping("/materials")
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @GetMapping("/materials/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable(value = "id") Long materialId)
            throws ResourceNotFoundException {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found for this id :: " + materialId));
        return ResponseEntity.ok().body(material);
    }

    @PostMapping("/materials")
    public Material createMaterial(@Valid @RequestBody MaterialDto materialDto) {
        return materialService.create(materialDto);
    }

    @PutMapping("/materials/{id}")
    public ResponseEntity<Material> updateMaterial(@PathVariable(value = "id") Long materialId,
                                                 @Valid @RequestBody Material materialDetails) throws ResourceNotFoundException {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found for this id :: " + materialId));

        material.setName(materialDetails.getName());
        material.setPrice(materialDetails.getPrice());
        material.setMeasureId(materialDetails.getMeasureId());
        final Material updatedMaterial = materialRepository.save(material);
        return ResponseEntity.ok(updatedMaterial);
    }

    @DeleteMapping("/materials/{id}")
    public Map<String, Boolean> deleteMaterial(@PathVariable(value = "id") Long materialId)
            throws ResourceNotFoundException {
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("Material not found for this id :: " + materialId));

        materialRepository.delete(material);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
