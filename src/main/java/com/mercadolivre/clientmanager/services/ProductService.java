package com.mercadolivre.clientmanager.services;

import com.mercadolivre.clientmanager.model.Product;
import com.mercadolivre.clientmanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Integer save(Product product) {
        return productRepository.save(product);
    }

    public Product find(Integer id) {
        return productRepository.find(id);
    }

    public void delete(Integer id){
        productRepository.delete(id);
    }

    public void update(Product product){
        productRepository.update(product);
    }
}
