package com.guptatyre.controller.admin;



import com.guptatyre.dto.RegisterAdminRequest;
import com.guptatyre.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminUserService adminUserService;

    @GetMapping("/admin/login")
    public String login(){
        return "admin/login";
    }

    @GetMapping("/admin/dashboard")
    public String dashBoard(){
        return "admin/dashboard";
    }


    @GetMapping("/admin/register")
    public String registerPage(Model model) {

        model.addAttribute("admin",
                new RegisterAdminRequest());

        return "admin/register";

    }

    @PostMapping("/admin/register")
    public String register(
            @ModelAttribute("admin") RegisterAdminRequest request) {

        adminUserService.register(request);

        return "redirect:/admin/login";

    }
}
