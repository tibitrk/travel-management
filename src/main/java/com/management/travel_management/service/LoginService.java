package com.management.travel_management.service;

import com.management.travel_management.model.Login;
import com.management.travel_management.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public Login validateUser(int empNo) {
        return loginRepository.findUser(empNo);
    }
    public Login loginEntry(Login login){
       return loginRepository.save(login);
    }
    public void updatePassword(Login login){
       loginRepository.save(login);
    }
    public Login findByEmpNo(int empNo){
        return loginRepository.findByEmpNo(empNo);
    }
}