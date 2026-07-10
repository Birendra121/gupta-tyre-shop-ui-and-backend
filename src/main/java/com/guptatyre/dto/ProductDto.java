package com.guptatyre.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Short description is required")
    private String shortDescription;

    private String description;

    @NotNull(message = "Brand is required")
    private Long brandId;

    @NotNull(message = "Category is required")
    private Long categoryId;

    @NotNull(message = "MRP is required")
    @DecimalMin(value = "0.0")
    private BigDecimal mrp;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.0")
    private BigDecimal sellingPrice;

    private Integer discountPercentage;

    @NotNull(message = "Stock is required")
    private Integer stockQuantity;

    @NotBlank(message = "SKU is required")
    private String sku;

    private Boolean featured = false;

    private Boolean active = true;

    private String image;

    // Generate Getters & Setters
}