package com.batisco.fastDev.controller;

import com.batisco.fastDev.dto.ProductDto;
import com.batisco.fastDev.sevice.DtoMapperService;
import com.batisco.fastDev.sevice.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class.getName());


    private final ProductService productService;
    private final DtoMapperService mapper;

    @Autowired
    public ProductController(ProductService productService,
                             DtoMapperService mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        logger.info("get all products");
        List<ProductDto> response = mapper.mapProductsToDto(productService.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getByName")
    public ResponseEntity<ProductDto> getByName(@RequestParam("name") String name) {
        logger.info("get by name");
        ProductDto response = mapper.mapProductToDto(productService.getByName(name));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto dto) {
        logger.info("Add new product = {}", dto);
        ProductDto response = mapper.mapProductToDto(
                productService.addProduct(mapper.mapToProduct(dto))
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto dto) {
        logger.info("Update product = {}", dto);
        ProductDto response = mapper.mapProductToDto(
                productService.updateProduct(mapper.mapToProduct(dto))
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam Long productId) {
        logger.info("Delete product with id = {}", productId);
        productService.deleteProduct(productId);
        return new ResponseEntity<>("product data delete successfully", HttpStatus.OK);
    }

}
