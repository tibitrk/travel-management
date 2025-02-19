package com.management.travel_management.controller;

import com.management.travel_management.model.Login;
import com.management.travel_management.model.User;
import com.management.travel_management.service.LoginService;
import com.management.travel_management.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String getLogin(@RequestParam int empNo, @RequestParam String password, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login login = loginService.validateUser(empNo);
        if (login !=null && login.getPassword().equals(password)) {
            String username = login.getUsername();
            session.setAttribute("empNo", empNo);
            session.setAttribute("uName", username);
            session.setAttribute("designation",login.getDesignation());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid Employee Number or Password");
            return "login";
        }

    }
    @GetMapping("/register")
    public String getReg(){
        return "registration";
    }
    @PostMapping("/register")
    public String insertReg(@RequestParam("empNo") int empNo,@RequestParam("empName") String empName,
                            @RequestParam("designation") String designation,@RequestParam("email") String email,
                            @RequestParam("password") String password, Model model){
        Login log = loginService.validateUser(empNo);
        if(log !=null){
            model.addAttribute("error", "Employee Number already exists!");
            return "registration";
        }
        Login login = new Login();
        login.setEmpNo(empNo);
        login.setUsername(empName);
        login.setDesignation(designation);
        login.setEmail(email);
        login.setPassword(password);
        loginService.loginEntry(login);
        model.addAttribute("success", "Registration successful!");
        return "login";
    }
    @GetMapping("/logout")
    public String logOut( Model model){
        model.addAttribute("logout", "LogOut Successful!");
        return "login";
    }
    @GetMapping("/forgot")
    public String forgot(){
        return "forgot";
  }
    @PostMapping("/forgotPass")
    public String forgotPass(@RequestParam int empNo, @RequestParam String password, Model model){

        Login login = loginService.findByEmpNo(empNo);
         if(login == null){
             model.addAttribute("error", "Employee Number Not found!");
             return "forgot";
         }
         login.setPassword(password);
         loginService.updatePassword(login);
          return "redirect:/login";
    }
}
