package com.guptatyre.controller;

import com.guptatyre.entity.Product;
import com.guptatyre.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class PublicProductController {

    private final ProductService productService;


    public PublicProductController(ProductService productService) {

        this.productService = productService;

    }

    @GetMapping
    public String products(Model model){
        model.addAttribute("products", productService.getActiveProducts());

        return "products";
    }

    @GetMapping("/{id}")
    public String productDetails(@PathVariable Long id, Model model){
       Product product = productService.getById(id);
        model.addAttribute("product", product);

        model.addAttribute(
                "relatedProducts",
                productService.getActiveProducts()
                        .stream()
                        .filter(p -> !p.getId().equals(id))
                        .limit(4)
                        .toList());

        return "product-details";
    }

}
