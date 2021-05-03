package com.example.company.controller;

import com.example.company.dto.MaterialDto;
import com.example.company.exception.ResourceNotFoundException;
import com.example.company.logging.Log;
import com.example.company.service.MaterialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
@AllArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    public List<MaterialDto> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> getMaterialById(@PathVariable(value = "id") Long materialId) throws ResourceNotFoundException {
        return materialService.getMaterialById(materialId);
    }

    @Log
    @PostMapping
    public MaterialDto createMaterial(@Valid @RequestBody MaterialDto materialDto) {
        return materialService.createMaterial(materialDto);
    }

    @Log
    @PutMapping("/{id}")
    public ResponseEntity<MaterialDto> updateMaterial(@PathVariable(value = "id") Long materialId,
                                                      @Valid @RequestBody MaterialDto materialDetails) throws ResourceNotFoundException {
        return materialService.updateMaterial(materialId, materialDetails);
    }

    @Log
    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable(value = "id") Long materialId) throws ResourceNotFoundException {
        materialService.deleteMaterial(materialId);
    }

}
