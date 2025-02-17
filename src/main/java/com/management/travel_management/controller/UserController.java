package com.management.travel_management.controller;

import com.management.travel_management.model.Login;
import com.management.travel_management.model.User;
import com.management.travel_management.service.LoginService;
import com.management.travel_management.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/home")
    public String getHome(Model model, HttpServletRequest request){
        User newUser = new User();
        model.addAttribute("user", newUser);

        HttpSession session = request.getSession();
        int empNo = (int) session.getAttribute("empNo");
        String uName = (String) session.getAttribute("uName");

        model.addAttribute("empNo", empNo);
        model.addAttribute("uName", uName);

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
                         @RequestParam(name = "startDate", required = false) String startDateStr,
                         @RequestParam(name = "endDate", required = false) String endDateStr,
                         Model model, HttpSession session){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = new Date(sdf.parse(startDateStr).getTime());
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = new Date(sdf.parse(endDateStr).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
      int no = (int) session.getAttribute("empNo");
      String des = (String) session.getAttribute("designation");

        System.out.println("startDate " + startDate);
        System.out.println("endDate " + endDate);
        List<User> users;

        if (empNo != null) {
            users = userService.getUserByEmpNo(empNo);
        } else if(startDate != null || endDate != null){
           users = userService.getUserFromDates(startDate,endDate);

        } else if("Manager".equals(des) || "HR".equals(des)){
           users = userService.getAllUsers();
            model.addAttribute("user", users);
            return "admin_report";
        }else{
            users = userService.getUserByEmpNo(no);
        }

        model.addAttribute("user", users);
        return "report";
    }
    @GetMapping("/report/edit/{id}")
    public String editReport(@PathVariable Long id, Model model){
        System.out.println("Received ID: " + id);
        model.addAttribute("user", userService.userById(id));
        return "edit_report";
    }

    @PostMapping("/report/{id}")
    public String updateUser(@PathVariable Long id, Model model, User user){
        model.addAttribute("user", userService.updateUser(user));

        User u = new User();
        u.setId(id);
        u.setEmpNo(user.getEmpNo());
        u.setEmpName(user.getEmpName());
        u.setStartDate(user.getStartDate());
        u.setEndDate(user.getEndDate());
        u.setDestination(user.getDestination());
        u.setPurpose(user.getPurpose());

        userService.updateUser(u);
        return "redirect:/report";
    }
    @GetMapping("/report/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return "redirect:/report";
    }


}
