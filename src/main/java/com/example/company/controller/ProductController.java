package com.example.company.controller;

import com.example.company.dto.ProductDto;
import com.example.company.exception.ResourceNotFoundException;
import com.example.company.logging.Log;
import com.example.company.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
        return productService.getProductById(productId);
    }

    @Log
    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @Log
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "id") Long productId,
                                                    @Valid @RequestBody ProductDto productDetails) throws ResourceNotFoundException {
        return productService.updateProduct(productId, productDetails);
    }

    @Log
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
        productService.deleteProduct(productId);
    }
}

