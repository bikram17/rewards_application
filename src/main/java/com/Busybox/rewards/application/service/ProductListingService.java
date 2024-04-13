package com.Busybox.rewards.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Busybox.rewards.application.model.ProductListing;
@Service
public interface ProductListingService {
    List<ProductListing> getAllProducts();

    ProductListing getProductById(Long id);

    ProductListing createProduct(ProductListing product);

    ProductListing updateProduct(Long id, ProductListing product);

    boolean deleteProduct(Long id);
}