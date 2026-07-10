package com.guptatyre.controller;

import com.guptatyre.dto.ContactMessageDto;
import com.guptatyre.entity.ContactMessage;
import com.guptatyre.service.ContactMessageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    private final ContactMessageService service;

    public ContactController(ContactMessageService service) {
        this.service = service;
    }


    @GetMapping("/contact")
    public String contact(Model model){

        if (!model.containsAttribute("contact")){
            model.addAttribute("contact", new ContactMessageDto());

        }
        return "contact";
    }
    @PostMapping("/contact")
    public String saveContact(@Valid @ModelAttribute("contact") ContactMessageDto dto, BindingResult result, Model model){
        if (result.hasErrors()){
            return "contact";
        }

        service.save(dto);

        model.addAttribute("success", "Thank you! Your enquiry has been submitted successfully.");
        model.addAttribute("contact", new ContactMessageDto());

        return "contact";
    }
}
