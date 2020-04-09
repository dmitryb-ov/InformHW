package com.example.demo.controllers;

import com.example.demo.dto.SignUpDto;
import com.example.demo.models.User;
import com.example.demo.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService service;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model){
        model.addAttribute("user",new User());
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@Validated @ModelAttribute("user") SignUpDto form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "sign_up";
        } else {
            service.signUp(form);
            return "redirect:/signUp";
        }
    }
}
