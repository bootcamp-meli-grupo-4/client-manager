package com.mercadolivre.clientmanager.resources;

import com.mercadolivre.clientmanager.model.Product;
import com.mercadolivre.clientmanager.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> allProducts(){
        List<Product> all = productService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        Integer id = productService.save(product);
        URI uri = URI.create(String.format("/product/%d", id));
        return ResponseEntity.created(uri).build();
    }
}
