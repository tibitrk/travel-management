package com.management.travel_management.controller;

import com.management.travel_management.model.Login;
import com.management.travel_management.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String getLogin(@RequestParam int empNo, @RequestParam String password, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Login login = loginService.validateUser(empNo);
        if (login !=null && login.getPassword().equals(password)) {
            session.setAttribute("empNo", empNo);
            String username = login.getUsername();
            session.setAttribute("uName", username);
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
                            @RequestParam("password") String password, Model model){
        Login login = new Login();
        login.setEmpNo(String.valueOf(empNo));
        login.setUsername(empName);
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
}
