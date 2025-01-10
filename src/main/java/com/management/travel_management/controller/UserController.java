package com.management.travel_management.controller;

import com.management.travel_management.model.User;
import com.management.travel_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @PostMapping("/home")
    public String userEntry(Model model, User user, RedirectAttributes redirectAttributes) {
        model.addAttribute("user", user);
        userService.userEntry(user);
        redirectAttributes.addFlashAttribute("successMessage", "Travel details successfully updated");
        return "redirect:/home";
    }

}
