package com.batisco.fastDev.sevice;

import com.batisco.fastDev.dal.ProductRepository;

import com.batisco.fastDev.model.Product;
import com.batisco.fastDev.model.exceptions.UserAlreadyHaveProductException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product addProduct(Product product) {
        try {
            return productRepository.saveAndFlush(product);
        } catch(Exception e) {
            throw new UserAlreadyHaveProductException(
                    "User " + product.getUserId() + " already have product with name " + product.getName(),
                    e
            );
        }
    }

    @Transactional
    public Product updateProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
