package com.management.travel_management.service;

import com.management.travel_management.model.User;
import com.management.travel_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User userEntry(User user){
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public List<User> getUserByEmpNo(int empNo) {
        return userRepository.findByEmpNo(empNo);
    }
    public List<User> getUserFromDates(Date startDate, Date endDate){
        return userRepository.findAllByRangeDate(startDate,endDate);
    }




}
