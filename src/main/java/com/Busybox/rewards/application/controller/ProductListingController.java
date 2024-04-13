package com.Busybox.rewards.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Busybox.rewards.application.model.ProductListing;
import com.Busybox.rewards.application.service.ProductListingService;

@RestController
@RequestMapping("/products")
public class ProductListingController {

    @Autowired
    private ProductListingService productListingService;

    @GetMapping()
    public ResponseEntity<List<ProductListing>> getAllProducts()         {
        List<ProductListing> products = productListingService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductListing> getProductById(@PathVariable Long id) {
        ProductListing product = productListingService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    
    
    
    @PostMapping
    public ResponseEntity<ProductListing> createProduct(@RequestBody ProductListing product) {
        ProductListing createdProduct = productListingService.createProduct(product);         
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /* payload 
     {
  "product_name": "Sample Product 1234",
  "product_price": 19.99,
  "product_description": "This is a sample product description.",
  "product_id": "SAMPLE1234",
  "product_category": "Electronics",
  "product_discount": 5.0,
  "availability": true
}      
     */
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductListing> updateProduct(@PathVariable Long id, @RequestBody ProductListing product) {
        ProductListing updatedProduct = productListingService.updateProduct(id, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productListingService.deleteProduct(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}