package com.yordan.karabelyov.Workshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String displayHome(Model model) {
        return "main/home";
    }

    @RequestMapping("/about")
    public String displayAbout() {
        return "about/about";
    }
    @RequestMapping("/contacts")
    public String displayContacts() {
        return "contacts/contacts";
    }


}
