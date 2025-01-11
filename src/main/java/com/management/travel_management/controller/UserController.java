package com.management.travel_management.controller;

import com.management.travel_management.model.User;
import com.management.travel_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;

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
        redirectAttributes.addFlashAttribute("successMessage", "Travel details updated successfully ");
        return "redirect:/home";
    }
    @GetMapping("/report")
    public String getAll(@RequestParam(required = false) Integer empNo,
                         @RequestParam(name = "start_date", required = false)
                         @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                         @RequestParam(name = "end_date", required = false)
                             @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate,
                         Model model){

        List<User> users;
        if (empNo != null) {
            users = userService.getUserByEmpNo(empNo);
        } else if(startDate != null || endDate != null){
           users = userService.getUserFromDates(startDate,endDate);

        } else {
            users = userService.getAllUsers();
        }

        model.addAttribute("user", users);
        return "report";
    }


}
