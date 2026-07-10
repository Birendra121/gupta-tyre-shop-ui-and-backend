package com.guptatyre.controller.admin;

import com.guptatyre.entity.Category;
import com.guptatyre.service.CategoryService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());

        return "admin/category-list";
    }

    @GetMapping("/new")
    public String addForm(Model model){
        model.addAttribute("category", new Category());

        return "admin/category-form";
    }

    @PostMapping("/save")
    public String save(Category category){
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model){

        model.addAttribute("category",
                categoryService.getById(id));

        return "admin/category-form";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        categoryService.delete(id);

        return "redirect:/admin/categories";

    }
}
