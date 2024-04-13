package com.Busybox.rewards.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Busybox.rewards.application.dao.ProductListingRepository;
import com.Busybox.rewards.application.model.ProductListing;
import com.Busybox.rewards.application.service.ProductListingService;

@Service
@Transactional
public class ProductListingServiceImpl implements ProductListingService {

    @Autowired
    private ProductListingRepository productListingRepository;

    @Override
    public List<ProductListing> getAllProducts() {
        return productListingRepository.findAll();
    }

    @Override
    public ProductListing getProductById(Long id) {
        return productListingRepository.findById(id).orElse(null);
    }

    @Override
    public ProductListing createProduct(ProductListing product) {
        return productListingRepository.save(product);
    }

    @Override
    public ProductListing updateProduct(Long id, ProductListing product) {
        if (productListingRepository.existsById(id)) {
            product.setId(id);
            return productListingRepository.save(product);
        }
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (productListingRepository.existsById(id)) {
            productListingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
