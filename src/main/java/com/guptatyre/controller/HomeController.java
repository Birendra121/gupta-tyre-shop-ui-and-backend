package com.guptatyre.controller;

import com.guptatyre.dto.ContactMessageDto;
import com.guptatyre.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {

        if (!model.containsAttribute("contact")) {
            model.addAttribute("contact", new ContactMessageDto());
        }

        model.addAttribute(
                "featuredProducts",
                productService.getFeaturedProducts());

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/tyres")
    public String tyres() {
        return "tyres";
    }

    @GetMapping("/accessories")
    public String accessories() {
        return "accessories";
    }

    @GetMapping("/services")
    public String services() {
        return "services";
    }
}