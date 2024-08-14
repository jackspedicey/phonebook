package com.msig.phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactPersonViewController {


    @GetMapping("/")
    public String homePage(Model model) {
        // Add any necessary data to the model
        return "index";
    }
}
