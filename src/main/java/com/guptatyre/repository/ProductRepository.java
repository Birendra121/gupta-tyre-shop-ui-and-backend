package com.guptatyre.repository;

import com.guptatyre.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    Optional<Product> findBySku(String sku);

    Optional<Product> findByName(String name);

    long countByActiveTrue();

    long countByStockQuantityLessThanEqualAndStockQuantityGreaterThan(
            Integer low,
            Integer greaterThan);

    long countByStockQuantity(Integer stock);

    List<Product> findByActiveTrueOrderByCreatedAtDesc();

    List<Product> findTop4ByFeaturedTrueAndActiveTrueOrderByCreatedAtDesc();

}
