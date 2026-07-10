package com.guptatyre.controller.admin;



import com.guptatyre.dto.ProductDto;
import com.guptatyre.mapper.ProductMapper;
import com.guptatyre.service.BrandService;
import com.guptatyre.service.CategoryService;
import com.guptatyre.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.UUID;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             BrandService brandService,
                             CategoryService categoryService) {

        this.productService = productService;
        this.brandService = brandService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(

            @RequestParam(required = false)
            String keyword,

            @RequestParam(required = false)
            Long brandId,

            @RequestParam(required = false)
            Long categoryId,

            @RequestParam(required = false)
            Boolean active,

            Model model) {

        model.addAttribute(
                "products",

                productService.searchProducts(
                        keyword,
                        brandId,
                        categoryId,
                        active));

        model.addAttribute(
                "brands",
                brandService.getAllBrands());

        model.addAttribute(
                "categories",
                categoryService.getAllCategories());

        model.addAttribute("keyword", keyword);
        model.addAttribute("brandId", brandId);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("active", active);


        model.addAttribute(
                "totalProducts",
                productService.totalProducts());

        model.addAttribute(
                "activeProducts",
                productService.activeProducts());

        model.addAttribute(
                "lowStockProducts",
                productService.lowStockProducts());

        model.addAttribute(
                "outOfStockProducts",
                productService.outOfStockProducts());

        return "admin/product-list";
    }

    @GetMapping("/new")
    public String add(Model model) {

        model.addAttribute("product",
                new ProductDto());

        loadDropdowns(model);

        return "admin/product-form";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("product") ProductDto dto,
            @RequestParam("imageFile") MultipartFile imageFile,
            Model model) {

        try {

            if (!imageFile.isEmpty()) {

                String fileName =
                        UUID.randomUUID() + "_" +
                                imageFile.getOriginalFilename();

                Path uploadPath =
                        Paths.get("src/main/resources/static/uploads/products");

                Files.createDirectories(uploadPath);

                imageFile.transferTo(
                        uploadPath.resolve(fileName));

                dto.setImage(fileName);

            }

            productService.save(dto);

            return "redirect:/admin/products";

        } catch (Exception ex) {

            model.addAttribute("product", dto);

            model.addAttribute("error",
                    ex.getMessage());

            loadDropdowns(model);

            return "admin/product-form";
        }

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model) {

        model.addAttribute("product",
                ProductMapper.toDto(productService.getById(id)));

        loadDropdowns(model);

        return "admin/product-form";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        productService.delete(id);

        return "redirect:/admin/products";
    }

    private void loadDropdowns(Model model) {

        model.addAttribute("brands",
                brandService.getAllBrands());

        model.addAttribute("categories",
                categoryService.getAllCategories());

    }

}