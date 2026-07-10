package com.guptatyre.service;

import com.guptatyre.dto.ProductDto;
import com.guptatyre.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getById(Long id);

    Product save(ProductDto dto);

    void delete(Long id);

    List<Product> searchProducts(
            String keyword,
            Long brandId,
            Long categoryId,
            Boolean active);


    long totalProducts();

    long activeProducts();

    long lowStockProducts();

    long outOfStockProducts();

    List<Product> getActiveProducts();

    List<Product> getFeaturedProducts();

}
