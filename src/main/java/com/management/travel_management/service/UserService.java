package com.management.travel_management.service;

import com.management.travel_management.model.User;
import com.management.travel_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User userEntry(User user){
        return userRepository.save(user);
    }
}
