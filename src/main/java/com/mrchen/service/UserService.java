package com.mrchen.service;

import com.mrchen.domain.User;
import com.mrchen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Mrchen
 * @version 1.0
 * @date 2020/9/25 10:27
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User getUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void insertUser(User user){
        if (user==null){
            return;
        }
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        userRepository.save(user);
    }
}
