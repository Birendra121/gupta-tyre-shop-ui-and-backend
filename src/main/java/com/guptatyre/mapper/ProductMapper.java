package com.guptatyre.mapper;


import com.guptatyre.dto.ProductDto;
import com.guptatyre.entity.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductDto toDto(Product product) {

        ProductDto dto = new ProductDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setShortDescription(product.getShortDescription());
        dto.setDescription(product.getDescription());

        dto.setMrp(product.getMrp());
        dto.setSellingPrice(product.getSellingPrice());

        dto.setDiscountPercentage(product.getDiscountPercentage());
        dto.setStockQuantity(product.getStockQuantity());

        dto.setSku(product.getSku());

        dto.setFeatured(product.getFeatured());
        dto.setActive(product.getActive());

        if (product.getBrand() != null) {
            dto.setBrandId(product.getBrand().getId());
        }

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
        }

        return dto;
    }
}
