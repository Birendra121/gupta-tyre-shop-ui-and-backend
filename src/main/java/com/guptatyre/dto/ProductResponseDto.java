package com.guptatyre.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private String name;

    private BigDecimal mrp;

    private BigDecimal sellingPrice;

    private Integer discountPercentage;
}
