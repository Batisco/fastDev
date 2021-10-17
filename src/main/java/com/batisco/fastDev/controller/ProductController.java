package com.batisco.fastDev.controller;

import com.batisco.fastDev.dto.ProductDto;
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


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody ProductDto dto) {
        try {
            productService.saveProduct(dto);
            logger.info("product data saved successfully. product ={}", dto);
            return new ResponseEntity<>("product data saved successfully", HttpStatus.CREATED);
        } catch(RuntimeException e) {
            logger.error("Fail to save product {}", dto, e);
            return new ResponseEntity<>("Fail to save product", HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestParam Long productId) {
        try {
            productService.deleteProduct(productId);
            logger.info("product data delete successfully. product id = {}", productId);
            return new ResponseEntity<>("product data delete successfully", HttpStatus.CREATED);
        } catch(RuntimeException e) {
            logger.error("Fail to delete product with id={}", productId, e);
            return new ResponseEntity<>("Fail to delete product", HttpStatus.BAD_GATEWAY);
        }
    }

}
