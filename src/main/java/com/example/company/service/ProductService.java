package com.example.company.service;

import com.example.company.dto.ProductDto;
import com.example.company.entity.Product;
import com.example.company.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(ProductDto productDto) {
        Product product = new Product();
        return updateFromDtoAndSave(product, productDto);
    }

    private Product updateFromDtoAndSave(Product product, ProductDto productDto) {
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setMeasureId(productDto.getMeasureId());
        product.setMaterials(productDto.getMaterials());
        product.setPrice(productDto.getPrice());
       return productRepository.save(product);
    }
}
