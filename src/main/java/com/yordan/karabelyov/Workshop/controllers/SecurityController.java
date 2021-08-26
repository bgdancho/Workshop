package com.yordan.karabelyov.Workshop.controllers;

import com.yordan.karabelyov.Workshop.model.UserAccount;
import com.yordan.karabelyov.Workshop.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/register")
    public String register(Model model){
        UserAccount userAccount = new UserAccount();
        model.addAttribute("userAccount",userAccount);

        return "security/register";
    }

    @PostMapping("/register/save")
    public String saveUser(Model model, UserAccount user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userAccountService.save(user);
        return "redirect:/";
    }


}
