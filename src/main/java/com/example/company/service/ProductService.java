package com.example.company.service;

import com.example.company.dto.ProductDto;
import com.example.company.entity.Product;
import com.example.company.exception.ResourceNotFoundException;
import com.example.company.repository.MaterialRepository;
import com.example.company.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final MaterialRepository materialRepository;

    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setMeasureId(productDto.getMeasureId());
        product.setMaterials(productDto.getMaterials().stream().map(material -> materialRepository.getOne(material.getId())).collect(Collectors.toCollection(TreeSet::new)));
        product.setPrice(productDto.getPrice());
        Product createdProduct = productRepository.save(product);
        return new ProductDto(createdProduct);
    }

    @Transactional
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<ProductDto> getProductById(Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        ProductDto productDto = new ProductDto(product);
        return ResponseEntity.ok().body(productDto);
    }

    @Transactional
    public ResponseEntity<ProductDto> updateProduct(Long productId, ProductDto productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setMeasureId(productDetails.getMeasureId());
        product.setMaterials(productDetails.getMaterials().stream().map(material -> materialRepository.getOne(material.getId())).collect(Collectors.toCollection(TreeSet::new)));
        final Product updatedProduct = productRepository.save(product);
        ProductDto updatedProductDto = new ProductDto(updatedProduct);
        return ResponseEntity.ok(updatedProductDto);
    }

    @Transactional
    public void deleteProduct(Long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        productRepository.delete(product);
    }

}
