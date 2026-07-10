package com.guptatyre.service;



import com.guptatyre.dto.ProductDto;
import com.guptatyre.entity.Brand;
import com.guptatyre.entity.Category;
import com.guptatyre.entity.Product;
import com.guptatyre.repository.BrandRepository;
import com.guptatyre.repository.CategoryRepository;
import com.guptatyre.repository.ProductRepository;
import com.guptatyre.service.ProductService;
import com.guptatyre.specification.ProductSpecification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              BrandRepository brandRepository,
                              CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();

    }

    @Override
    public Product getById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

    }

    @Override
    public Product save(ProductDto dto) {

        validateDuplicate(dto);

        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() ->
                        new RuntimeException("Brand not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        Product product;

        if (dto.getId() == null) {

            product = new Product();

        } else {

            product = getById(dto.getId());

        }

        product.setName(dto.getName());

        product.setShortDescription(dto.getShortDescription());

        product.setDescription(dto.getDescription());

        product.setBrand(brand);

        product.setCategory(category);

        product.setMrp(dto.getMrp());

        product.setSellingPrice(dto.getSellingPrice());

        product.setDiscountPercentage(dto.getDiscountPercentage());

        product.setStockQuantity(dto.getStockQuantity());

        product.setSku(dto.getSku());

        if (dto.getImage() != null) {

            product.setImage(dto.getImage());

        }

        product.setFeatured(dto.getFeatured());

        product.setActive(dto.getActive());

        return productRepository.save(product);

    }

    @Override
    public void delete(Long id) {

        productRepository.deleteById(id);

    }

    private void validateDuplicate(ProductDto dto) {

        Optional<Product> sku =
                productRepository.findBySku(dto.getSku());

        if (sku.isPresent()
                && !sku.get().getId().equals(dto.getId())) {

            throw new RuntimeException("SKU already exists.");

        }

        Optional<Product> name =
                productRepository.findByName(dto.getName());

        if (name.isPresent()
                && !name.get().getId().equals(dto.getId())) {

            throw new RuntimeException("Product already exists.");

        }

    }

    @Override
    public List<Product> searchProducts(
            String keyword,
            Long brandId,
            Long categoryId,
            Boolean active) {

        return productRepository.findAll(
                ProductSpecification.filter(
                        keyword,
                        brandId,
                        categoryId,
                        active));

    }

    @Override
    public long totalProducts() {

        return productRepository.count();

    }

    @Override
    public long activeProducts() {

        return productRepository.countByActiveTrue();

    }

    @Override
    public long lowStockProducts() {

        return productRepository
                .countByStockQuantityLessThanEqualAndStockQuantityGreaterThan(
                        10,
                        0);

    }

    @Override
    public long outOfStockProducts() {

        return productRepository.countByStockQuantity(0);

    }

    @Override
    public List<Product> getActiveProducts() {
        return productRepository.findByActiveTrueOrderByCreatedAtDesc();
    }

    @Override
    public List<Product> getFeaturedProducts() {

        return productRepository
                .findTop4ByFeaturedTrueAndActiveTrueOrderByCreatedAtDesc();

    }

}