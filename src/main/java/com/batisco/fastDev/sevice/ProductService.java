package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dal.ProductRepository;
import com.batisco.fastDev.dto.ProductDto;
import com.batisco.fastDev.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().
                map(this::mapToDto).
                toList();
    }

    public void saveProduct(ProductDto product) {
        productRepository.save(mapToProduct(product));
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }


    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setUserId(product.getUserId());
        dto.setName(product.getName());
        return dto;
    }

    private Product mapToProduct(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setUserId(dto.getUserId());
        product.setName(dto.getName());
        return product;
    }

}
