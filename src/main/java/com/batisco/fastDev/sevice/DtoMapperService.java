package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dto.ProductDto;
import com.batisco.fastDev.model.Product;

import org.springframework.stereotype.Component;

@Component
public class DtoMapperService {

    public DtoMapperService() {

    }

    public ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setUserId(product.getUserId());
        dto.setName(product.getName());
        return dto;
    }

    public Product mapToProduct(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setUserId(dto.getUserId());
        product.setName(dto.getName());
        return product;
    }

}
