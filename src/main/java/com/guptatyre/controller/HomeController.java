package com.guptatyre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("tyres")
    public String tyres(){
        return "tyres";
    }

    @GetMapping("/accessories")
    public String accessories(){
        return "accessories";
    }

    @GetMapping("/services")
    public String services(){
        return "services";
    }
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}
