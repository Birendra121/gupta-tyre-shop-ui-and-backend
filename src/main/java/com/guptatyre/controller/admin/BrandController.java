package com.guptatyre.controller.admin;

import com.guptatyre.entity.Brand;
import com.guptatyre.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/admin/brands")
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {

        model.addAttribute("brands",
                service.getAllBrands());

        return "admin/brand-list";
    }

    @GetMapping("/new")
    public String newBrand(Model model) {

        model.addAttribute("brand",
                new Brand());

        return "admin/brand-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Brand brand, @RequestParam("logoFile")
    MultipartFile logoFile, Model model) {

        try {

            if(!logoFile.isEmpty()){

                String fileName=
                        UUID.randomUUID()+"_"+
                                logoFile.getOriginalFilename();

                Path uploadPath=
                        Paths.get("src/main/resources/static/uploads/brands");

                Files.createDirectories(uploadPath);

                logoFile.transferTo(
                        uploadPath.resolve(fileName));

                brand.setLogo(fileName);

            }

            service.save(brand);

            return "redirect:/admin/brands";

        } catch (RuntimeException | IOException ex) {

            model.addAttribute("brand", brand);
            model.addAttribute("error", ex.getMessage());

            return "admin/brand-form";

        }

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model) {

        model.addAttribute("brand",
                service.getById(id));

        return "admin/brand-form";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "redirect:/admin/brands";

    }

}