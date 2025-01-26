package com.management.travel_management.controller;

import com.management.travel_management.model.Login;
import com.management.travel_management.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String getLogin(@RequestParam int empNo, @RequestParam String password, Model model) {
        System.out.println("emp no " + empNo);
        System.out.println("password " + password);
        Login login = loginService.validateUser(empNo);
        if (login !=null && login.getPassword().equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid Employee Number or Password");
            return "login";
        }
    }

}
