package com.management.travel_management.controller;

import com.management.travel_management.model.User;
import com.management.travel_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String getHome(Model model){
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "home";
    }
}
